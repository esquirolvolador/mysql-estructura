SELECT nombre FROM tienda.producto;
SELECT nombre,precio FROM tienda.producto;
SELECT * FROM tienda.producto;
SELECT nombre,concat(precio,"€"),concat("$",precio) FROM tienda.producto;
SELECT nombre AS nombre_de_producto, concat(precio,"€") AS euros, concat("$",precio) AS dòlars FROM tienda.producto;
SELECT UPPER(nombre),precio FROM tienda.producto;
SELECT LOWER(nombre),precio FROM tienda.producto;
SELECT nombre, UPPER(SUBSTRING(nombre, 1, 2)) AS INICIALES FROM tienda.fabricante;
SELECT nombre,ROUND(precio,"€") FROM tienda.producto;
SELECT nombre, FLOOR(precio) FROM tienda.producto;
SELECT codigo_fabricante FROM tienda.producto;
SELECT DISTINCT codigo_fabricante FROM tienda.producto;
SELECT nombre FROM tienda.fabricante order by nombre ASC;
SELECT nombre FROM tienda.fabricante order by nombre DESC;
SELECT nombre, precio FROM tienda.producto order by nombre DESC, precio DESC;
SELECT * FROM tienda.fabricante LIMIT 5;
SELECT * FROM tienda.fabricante LIMIT 3,2;
SELECT nombre, precio FROM tienda.producto order by precio ASC LIMIT 0,1;
SELECT nombre, precio FROM tienda.producto order by precio DESC LIMIT 0,1;
SELECT * FROM tienda.fabricante where codigo = 2;

select nombre, apellido1, apellido2 from universidad.persona WHERE tipo = 'alumno' order by apellido1 ASC, apellido2 ASC, nombre ASC ;
select nombre, apellido1, apellido2 from universidad.persona WHERE telefono IS NULL AND tipo = 'alumno';
select * from universidad.persona WHERE fecha_nacimiento >= '1999-01-01' AND fecha_nacimiento <= '1999-12-31'AND tipo = 'alumno';
select * from universidad.persona WHERE telefono IS NULL AND tipo = 'profesor' AND nif LIKE '%K';
select nombre from universidad.asignatura WHERE cuatrimestre= 1 AND curso = 3 AND id_grado= 7;
select apellido1, apellido2, persona.nombre, departamento.nombre from universidad.persona, universidad.departamento order by apellido1 ASC, apellido2 ASC, persona.nombre ASC;
select anyo_inicio, anyo_fin, asignatura.nombre, nif from universidad.curso_escolar, universidad.asignatura, universidad.persona WHERE nif = '26902806M';
select departamento.nombre from universidad.grado, universidad.departamento WHERE grado.nombre = 'Grado en Ingeniería Informática (Plan 2015)';
select persona.nombre from universidad.persona, universidad.curso_escolar WHERE anyo_inicio = '2018';

Select departamento.nombre, apellido1, apellido2, persona.nombre, tipo from universidad.persona  RIGHT JOIN universidad.profesor ON persona.id = profesor.id_profesor RIGHT JOIN universidad.departamento ON profesor.id_departamento = departamento.id order by departamento.nombre ASC, apellido1 ASC, apellido2 ASC;
Select persona.nombre from universidad.persona  LEFT JOIN universidad.profesor ON persona.id = profesor.id_profesor LEFT JOIN universidad.departamento ON profesor.id_departamento = departamento.id where departamento.id is NULL;
Select departamento.nombre from universidad.persona  RIGHT JOIN universidad.profesor ON persona.id = profesor.id_profesor RIGHT JOIN universidad.departamento ON profesor.id_departamento = departamento.id where id_departamento is NULL;
Select persona.nombre, asignatura.nombre from universidad.persona  LEFT JOIN universidad.profesor ON persona.id = profesor.id_profesor LEFT JOIN universidad.asignatura ON profesor.id_profesor = asignatura.id_profesor  where asignatura.id_profesor is NULL;
Select persona.nombre, asignatura.nombre from universidad.persona  RIGHT JOIN universidad.profesor ON persona.id = profesor.id_profesor RIGHT JOIN universidad.asignatura ON profesor.id_profesor = asignatura.id_profesor where asignatura.id_profesor is NULL;

SELECT tipo, COUNT(*) FROM universidad.persona WHERE tipo = 'alumno' GROUP BY tipo;
SELECT fecha_nacimiento, COUNT(*) FROM universidad.persona WHERE fecha_nacimiento >= '1999-01-01' AND fecha_nacimiento <= '1999-12-31' AND tipo = 'alumno' GROUP BY tipo;
Select departamento.nombre, count(*) from universidad.persona RIGHT JOIN universidad.profesor ON persona.id = profesor.id_profesor RIGHT JOIN universidad.departamento ON profesor.id_departamento = departamento.id GROUP BY departamento.nombre order by count(*);
Select departamento.nombre, count(id_profesor) from universidad.departamento LEFT JOIN universidad.profesor ON departamento.id = profesor.id_departamento GROUP BY departamento.nombre;
select grado.nombre, count(asignatura.id) from universidad.asignatura, universidad.grado group by grado.nombre HAVING count(asignatura.id) >= 40;
select * from universidad.persona WHERE tipo = 'alumno' order by fecha_nacimiento ASC LIMIT 1;
