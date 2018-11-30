SELECT data_reserva, horario_inicio, horario_termino, id_laboratorio FROM reservas
WHERE 
   (
       (horario_inicio BETWEEN '07:10' AND '09:10') OR 
       (horario_termino    BETWEEN '07:10' AND '09:10') OR 
       ('07:10' BETWEEN horario_inicio AND horario_termino  ) OR 
       ('09:10' BETWEEN horario_inicio AND horario_termino   )
   )
AND data_reserva = '2018-11-29' AND id_laboratorio = 2;