CREATE OR REPLACE FUNCTION validate_email_format(email TEXT)
RETURNS BOOLEAN AS $$
BEGIN
    RETURN email ~ '^[a-zA-Z0-9!#$%&''*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&''*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$';
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION check_email_uniqueness(new_email TEXT, user_id BIGINT DEFAULT NULL)
RETURNS BOOLEAN ASS $$
BEGIN
    RETURN NOT EXISTS (
        SELECT 1
        FROM person
        WHERE email = new_email AND (user_id IS NULL OR id != user_id)
    );
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION validate_email()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT validate_email_format(NEW.email) THEN
        RAISE EXCEPTION 'Invalid email format.';
    END IF;

    IF NOT check_email_uniqueness(NEW.email, NEW.id) THEN
        RAISE EXCEPTION 'Email already in use.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
