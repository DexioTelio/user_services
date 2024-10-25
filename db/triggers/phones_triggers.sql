CREATE TRIGGER phone_presence_trigger
AFTER INSERT ON person
FOR EACH ROW
EXECUTE FUNCTION check_phone_before_insert();

CREATE TRIGGER validate_phone_trigger
AFTER INSERT ON phones
FOR EACH ROW
EXECUTE FUNCTION validate_phone();