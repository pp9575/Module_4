SELECT count (id)
    FROM "user"
    UNION ALL
    SELECT count (id)
    FROM "post"
    UNION ALL
    SELECT count (id)
    FROM "comment"
    UNION ALL
    SELECT count (id)
    FROM "like";

