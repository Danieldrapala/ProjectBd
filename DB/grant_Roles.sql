DROP ROLE IF EXISTS administrator;
CREATE ROLE administrator;
GRANT ALL PRIVILEGES ON *.* TO administrator WITH GRANT OPTION;

DROP ROLE IF EXISTS nauczyciel;
CREATE ROLE nauczyciel;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectAllStudents TO nauczyciel;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectClass TO nauczyciel;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.modifyStudent TO nauczyciel;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.checkStudentPrivileges TO nauczyciel;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.deleteOpinion TO uczen; -- about me


DROP ROLE IF EXISTS uczen;
CREATE ROLE uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.deleteOpinion TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.addOpinion TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.modifyOpinion TO uczen; -- my opinion
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.joinCourse TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.leaveCourse TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectClass TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectOpenCourses TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectClassLanguageLevel TO uczen;
GRANT EXECUTE ON PROCEDURE `SzkolaJezykowa`.selectTeacherInfo TO uczen;