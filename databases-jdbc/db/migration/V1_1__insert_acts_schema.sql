CREATE VIEW acts_with_record_label AS
    SELECT acts.name, acts.recordlabel
    FROM acts
    WHERE acts.recordlabel IS NOT NULL
    ORDER BY acts.name;

INSERT INTO acts (id, name, recordLabel)
VALUES (1, 'Foo Feathers', 'Copitol');
INSERT INTO acts (id, name, recordLabel)
VALUES (2, 'The Bottles', 'Banana');
INSERT INTO acts (id, name)
VALUES (3, 'BAAB');
INSERT INTO acts (id, name)
VALUES (4, 'Alede');
INSERT INTO acts (id, name)
VALUES (5, 'Dana Lead Rey');
INSERT INTO acts (id, name)
VALUES (6, 'Led Balloon');
INSERT INTO acts (id, name)
VALUES (7, 'Sheryl Rook');