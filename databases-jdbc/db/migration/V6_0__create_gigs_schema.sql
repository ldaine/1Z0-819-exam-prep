CREATE TABLE gigs
(
    id          SERIAL PRIMARY KEY,
    venueId     Int           not null,
    actId       Int           not null,
    ticketsSold Int           not null,
    price       decimal(4, 2) not null,
    Date        Date          not null,

    FOREIGN KEY (venueId) REFERENCES venues (id),
    FOREIGN KEY (actId) REFERENCES acts (id)
);
