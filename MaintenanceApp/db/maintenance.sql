CREATE TABLE IF NOT EXISTS sites (
    site_id   SERIAL PRIMARY KEY,
    type         VARCHAR(20),
    size         VARCHAR(20),
    maintenance  INTEGER,
    paid         INTEGER
);

select * from site;

CREATE TABLE IF NOT EXISTS users (
    user_id  INTEGER PRIMARY KEY,
    name     VARCHAR(20),
    site_id  INTEGER,
    CONSTRAINT fk_users_site
        FOREIGN KEY (site_id)
        REFERENCES sites(site_id)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);
select * from users;