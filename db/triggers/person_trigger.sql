CREATE TRIGGER validate_email_trigger
AFTER INSERT ON person
FOR EACH ROW
EXECUTE FUNCTION validate_email();