CREATE VIEW acts_with_record_label AS
    SELECT acts.name, acts.recordlabel
    FROM acts
    WHERE acts.recordlabel IS NOT NULL
    ORDER BY acts.name;
