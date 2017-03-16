BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE coffees';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
;;
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE suppliers';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
;;
create table suppliers
  (sup_id number(10) not null primary key,
  sup_name varchar2(40) not null,
  street varchar2(40) not null,
  city varchar2(20) not null,
  state char(2) not null,
  zip char(5)
)
;;
create table coffees
  (cof_name varchar2(32) not null,
  sup_id number not null,
  price numeric(10,2) not null,
  sales number not null,
  total number not null,
  primary key (cof_name),
  foreign key (sup_id) references suppliers (sup_id))
;;
create or replace procedure list_coffee_names(rs OUT SYS_REFCURSOR)
as
begin
    open rs for select cof_name
    from coffees
    order by cof_name;
end;
;;
create or replace procedure show_coffee_suppliers(rs OUT SYS_REFCURSOR)
as
  begin
    open rs for select suppliers.sup_name, coffees.cof_name
    from suppliers, coffees
    where suppliers.sup_id = coffees.sup_id
    order by sup_name;
  end;
;;
create or replace procedure get_supplier_of_coffee(coffeename in varchar2, suppliername out varchar2)
as
  begin
    select suppliers.sup_name into suppliername
      from suppliers, coffees
      where suppliers.sup_id = coffees.sup_id
      and coffees.cof_name = coffeename;
  end;
;;