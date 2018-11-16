SELECT * FROM datas
WHERE 
   (
       (horario_inicio BETWEEN '13:15' AND '14:00') OR 
       (horario_termino    BETWEEN '13:15' AND '14:00') OR 
       ('13:15' BETWEEN horario_inicio AND horario_termino  ) OR 
       ('14:00' BETWEEN horario_inicio AND horario_termino   )
   )
AND data_reserva = '2018-11-13';