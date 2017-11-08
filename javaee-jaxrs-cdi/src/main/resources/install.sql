--- run this file againest mysql database
use test;

drop table if exists coffees;

drop table if exists suppliers;

create table suppliers
  (sup_id integer not null,
  sup_name varchar(40) not null,
  street varchar(40) not null,
  city varchar(20) not null,
  state char(2) not null,
  zip char(5),
  primary key (sup_id));

create table coffees
  (cof_name varchar(32) not null,
  sup_id int not null,
  price numeric(10,2) not null,
  sales integer not null,
  total integer not null,
  primary key (cof_name),
  foreign key (sup_id) references suppliers (sup_id));

insert into suppliers values
    (49,  'superior coffee', '1 party place', 'mendocino', 'ca', '95460'),
    (101, 'acme, inc.', '99 market street', 'groundsville', 'ca', '95199'),
    (150, 'the high ground', '100 coffee lane', 'meadows', 'ca', '93966'),
    (456, 'restaurant supplies, inc.', '200 magnolia street', 'meadows', 'ca', '93966'),
    (927, 'professional kitchen', '300 daisy avenue', 'groundsville', 'ca', '95199');

insert into coffees values
    ('colombian',          101, 7.99, 0, 0),
    ('french_roast',       49,  8.99, 0, 0),
    ('espresso',           150, 9.99, 0, 0),
    ('colombian_decaf',    101, 8.99, 0, 0),
    ('french_roast_decaf', 049, 9.99, 0, 0);

drop procedure if exists list_coffee_names;

delimiter //
create procedure list_coffee_names()
begin
    select cof_name
    from coffees
    order by cof_name;
end//

drop procedure if exists show_coffee_suppliers;
delimiter //
create procedure show_coffee_suppliers()
  begin
    select suppliers.sup_name, coffees.cof_name
    from suppliers, coffees
    where suppliers.sup_id = coffees.sup_id
    order by sup_name;
  end//

drop procedure if exists get_supplier_of_coffee;

delimiter //
create procedure get_supplier_of_coffee(in coffeename varchar(32), out suppliername varchar(40))
  begin
    select suppliers.sup_name into suppliername
      from suppliers, coffees
      where suppliers.sup_id = coffees.sup_id
      and coffeename = coffees.cof_name;
    select suppliername;
  end//

drop procedure if exists raise_price;

delimiter //
create procedure raise_price(in coffeename varchar(32), in maximumpercentage float, in newpriceinput numeric(10,2), out newpriceoutput numeric(10,2))
  begin
    main: begin
      declare maximumnewprice numeric(10,2);
      declare oldprice numeric(10,2);
      select coffees.price into oldprice
        from coffees
        where coffees.cof_name = coffeename;
      set maximumnewprice = oldprice * (1 + maximumpercentage);
      if (newpriceinput > maximumnewprice)
        then set newpriceoutput = maximumnewprice;
      end if;
      if (newpriceoutput <= oldprice)
        then set newpriceoutput = oldprice;
        leave main;
      end if;
      update coffees
        set coffees.price = newpriceoutput
        where coffees.cof_name = coffeename;
      select newpriceoutput;
    end main;
  end//

