SELECT c.code AS course_code, c.name, c.description, u.first_name, u.last_name, c.max_capacity, c.status
FROM tb_course AS c
LEFT JOIN tb_teacher_courses AS inter ON c.code = inter.course_code
LEFT JOIN tb_user AS u ON inter.teacher_id = u.id;