const { Pool } = require('pg'); // Добавьте эту строку для импорта Pool из модуля pg
const express = require('express');
const bodyParser = require('body-parser');
const path = require('path'); 

const app = express();
const pool = new Pool({
  user: 'postgres',
  host: 'localhost',
  database: '111',
  password: '1234',
  port: 5433,
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }))

// Отправляем HTML-форму при GET-запросе на /index
app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, 'index.html'));
});

// Обработчик POST-запроса на /res для сохранения данных в БД
app.post('/index', function (req, res) {
  const Name = req.body.Name;
  const Phone_number = req.body.Phone_number;
  const Persons = req.body.Persons;
  const Date_rez = req.body.Date_rez;
  const Time_rez = req.body.Time_rez;
  const Message = req.body.Message;

  pool.query("INSERT INTO bronirovanie (Name, Phone_number, Persons, Date_rez, Time_rez, Message) VALUES ($1, $2, $3, $4, $5, $6)", [Name, Phone_number, Persons, Date_rez, Time_rez, Message], function (error, result) {
    if (error) {
      console.error('Ошибка выполнения запроса:', error);
      res.status(500).send('Внутренняя ошибка сервера');
    } else {
        res.send('Данные сохранены в базе данных');
    }
  });
});

app.listen(3000, () => {
    console.log('Сервер слушает порт 3000');
  });
