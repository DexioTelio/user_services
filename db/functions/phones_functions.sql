CREATE OR REPLACE FUNCTION check_phone_before_insert()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM phones WHERE person_id = NEW.id) THEN
        RAISE EXCEPT 'The user must have at least one telephone number.'
    END IF;
END;
$$ LANGUAGE plpgsql;