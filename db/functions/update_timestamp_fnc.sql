CREATE OR REPLACE FUNCTION update_timestamp_fnc()
RETURN TRIGGER AS $$
BEGIN
    NEW.update_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;