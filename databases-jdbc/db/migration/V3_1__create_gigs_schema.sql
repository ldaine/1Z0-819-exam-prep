PREPARE gig_report (Date, Date) AS
    SELECT gigs.date, acts.name 'Act', acts.recordlabel, venues.name 'Venue', ticketsSold, venues.capacity
    FROM gigs
             JOIN acts ON acts.id = gigs.actId
             JOIN venues ON venues.id = gigs.venueId
    WHERE gigs.date >= $1
      AND gigs.date <= $2
    ORDER BY gigs.date;

-- returns the total sales in the out parameter
create procedure GetTotalSales(OUT sales decimal(8, 2))
begin
    select sum(currentvalue) 'totalsales' from
        (select ticketsSold, price, ticketsSold*price 'currentvalue'
         from gigs) salestable
    into sales;
end;

CREATE VIEW total_sales AS
    SELECT sum(currentvalue) 'totalsales' FROM
        (SELECT ticketsSold, price, ticketsSold*price 'currentvalue'
         FROM gigs) salestable
    INTO sales;

-- call like this:
-- set @newprice = 11;
--
-- call SetNewprice(1, 0.1, @newprice);
--
-- select @newprice;
-- The idea is to see if we can set a new price, it takes the current price and applies a percentage rise
-- if the new price is less than the maximum then it is set and returned in the inout parameter
-- if the proposed new price is too large then the value is not changed and the current value is returned in the inout parameter
create procedure SetNewprice(IN gigid int, IN percentage decimal(8,2), inout maxprice decimal(8,2))
begin
    declare gigprice decimal(8,2) default 0.0;
    declare proposedprice decimal(8,2);


    set gigprice = (select max(price) from gigs where id = gigid);

    set proposedprice = gigprice + (gigprice * percentage);

    if (proposedprice < maxprice)
    then
        set maxprice = proposedprice;
        update gigs set price = proposedprice where id = gigid;
    else
        set maxprice = gigprice;
    end if;
end;


INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (1, 1, 10.5, 90, '2022-11-04');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (2, 1, 10.5, 110, '2022-11-05');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (3, 1, 10.5, 170, '2022-11-06');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (4, 1, 10.5, 20, '2022-11-08');

INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (1, 2, 12.99, 91, '2022-11-05');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (2, 2, 12.99, 113, '2022-11-04');

INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (3, 3, 4.99, 153, '2022-11-07');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (4, 4, 4.99, 10, '2022-11-04');

INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (1, 5, 14.99, 153, '2022-11-06');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (2, 5, 14.99, 101, '2022-11-10');

INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (1, 6, 14.99, 153, '2022-11-07');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (2, 6, 14.99, 101, '2022-11-09');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (4, 6, 9.99, 20, '2022-11-05');

INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (2, 7, 15.99, 150, '2022-11-08');
INSERT INTO gigs(venueId, actId, price, ticketsSold, Date)
VALUES (3, 7, 15.5, 101, '2022-11-04');