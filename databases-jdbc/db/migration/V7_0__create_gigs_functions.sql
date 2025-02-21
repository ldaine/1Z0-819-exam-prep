CREATE OR REPLACE FUNCTION gig_report(start_date DATE, end_date DATE)
RETURNS TABLE(
    date DATE,
    act TEXT,
    recordlabel TEXT,
    venue VARCHAR(100),
    ticketsSold INTEGER,
    capacity INTEGER
) AS $$
BEGIN
    RETURN QUERY
    SELECT gigs.date, acts.name AS act, acts.recordlabel, venues.name AS venue, gigs.ticketsSold, venues.capacity
    FROM gigs
    JOIN acts ON acts.id = gigs.actId
    JOIN venues ON venues.id = gigs.venueId
    WHERE gigs.date >= start_date
      AND gigs.date <= end_date
    ORDER BY gigs.date;
END;
$$ LANGUAGE plpgsql;

-- returns the total sales in the out parameter
CREATE OR REPLACE FUNCTION get_total_sales()
RETURNS DECIMAL(8, 2) AS $$
DECLARE
    sales DECIMAL(8, 2);
BEGIN
    SELECT SUM(currentvalue) INTO sales
    FROM (
        SELECT ticketsSold, price, ticketsSold * price AS currentvalue
        FROM gigs
    ) salestable;

    RETURN sales;
END;
$$ LANGUAGE plpgsql;
