create or replace function count_courses_by_price_and_days(price number, days number) return number
as
    resultval number;
begin
    select count(*) into resultval
    from courses
    where caprice = price and cdur = days;
    return resultval;
end;
/

select count_courses_by_price_and_days(550,3) as nbr_of_courses
from dual;

create or replace procedure update_courseprice(course_id char, newprice number)
as
begin
    update courses
    set caprice=newprice
    where cid=course_id;
end;
/

exec update_courseprice('7800', 555.);
select * from courses;

select count_courses_by_price_and_days(550,3)
from dual