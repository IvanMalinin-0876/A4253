
<?php

$query = $_GET["q"];

$titletext ='
{
  "status": "ok",
  "item":[
{
  "status": "ok",
  "id" : "1",
  "name" : "Тестовая книга № 5643",
  "pages": "421",
  "author" : "Букин Михаил Викторович"
},
{

  "id" : "2",
  "name" : "Тестовая книга № 6734",
  "pages": "153",
  "author" : "Велько Петр Иванович"
},

{

  "id" : "3",
  "name" : "Тестовая книга № 8324",
  "pages": "214",
  "author" : "Иванов Иван Иванович"
}
]
}
';

$myArray = json_decode($titletext);
header('Content-Type: application/json');
echo json_encode($myArray);
?>