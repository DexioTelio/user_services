CREATE TRIGGER update_timestamp_trigger
BEFORE INSERT OR UPDATE ON person
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();

CREATE TRIGGER update_timestamp_trigger
BEFORE INSERT OR UPDATE ON phones
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();

CREATE TRIGGER update_timestamp_trigger
BEFORE INSERT OR UPDATE ON address
FOR EACH ROW
EXECUTE FUNCTION update_timestamp_fnc();