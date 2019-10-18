Задание №1<br>
Написать программу для поиска заданного текста в лог файлах.<br>
Пользователь должен иметь возможность указать папку в сети или на жестком диске, в которой будет происходить поиск заданного текста, включая все вложенные папки.<br>
Должна быть возможность ввода текста поиска и ввода типа расширения файлов, в которых будет осуществляться поиск(расширение по умолчанию *.log).<br>
Результаты поиска можно вывести в левой части приложения в виде дерева файловой системы только те файлы в которых был обнаружен заданный текст.<br>
В правой части приложения выводить содержимое файла с возможностью навигации по найденному тексту (выделить все, вперед/назад).<br>
Плюсом будет многопоточность приложения, «не замораживание» приложения на время поиска, возможность открывать «большие» (более 1Г) <br>файлы и осуществлять по ним быструю навигацию, возможность открывать файлы в новых «табах», т. е. использовать TabFolder или MDI.
<br>Для отображения разрешается использовать любые Java GUI-фреймворки (AWT, Swing, SWT, JavaFX, NetBeans Platform и т.п.).<br>
Приложение может быть как десктопным, так и веб-клиентом.
 
Задание будет оцениваться по следующим критериям:
∙  скорость поиска в файлах заданного текста и скорость навигации по открытому файлу;
∙  приятный и интуитивно понятный интерфейс приложения;
∙  краткий и понятный исходный код.

This program can make search text in files on your hardware with specific extension.<br>
The default extension is log (you will see it on textbox when programm will be run)<br>
You can find big files (on my pc this program find search text in txt file which size is about 600mb.<br>
 It works around 15 seconds.<br> Because it doesnt read all file - it reads parts of files.<br>
 But you can work only with one file. You can't open simultaniously two files in two tabs; <br>
 You can click on file when program will find text and build tree and you will see text of the file.<br>
 You can scroll and see different pages.<br>
 You cant receive memory error, because it keeps only 1024*3 bytes of text, and on the next page it will remove previously
 <br> page and add new.
