drop procedure if exists insert_new_supplier;
;;
create procedure insert_new_supplier(in name varchar(250))
begin
    insert into supplier (name) values (name);
end;
;;