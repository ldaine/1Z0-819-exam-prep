CREATE OR REPLACE FUNCTION get_acts()
RETURNS TABLE(name TEXT, recordlabel TEXT) AS $$
BEGIN
    RETURN QUERY 
    SELECT acts.name as name, acts.recordlabel as recordLabel
    FROM acts
    WHERE acts.recordlabel IS NOT NULL
    ORDER BY acts.name;
END;
$$ LANGUAGE plpgsql;
