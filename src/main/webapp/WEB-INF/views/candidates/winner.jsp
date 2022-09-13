<%-- 
    Document   : winer
    Created on : 19 июл. 2022 г., 22:10:46
    Author     : Dima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset='utf-8'>s
    </head>
    <body>
        <h1>
            <p align="center">
                <font color=#D8BFD8 size="14"> 
                ${fistWinnerFistName}
                ${fistWinnerLastName}
                ${secondWinnerFistName}
                ${secondWinnerLastName}  
                </font>
            </p>
        </h1>
         <h1><p align="center"><font color=#D8BFD8 size="14"> ${winners}</font></p></h1>        
        
        <h1>
            <p align="center">
                <font color=#D8BFD8 size="15"> 
                    ${winner} ${WinnerFistName} ${WinnerLastName}
                </font>
            </p>
        </h1>
       
        <style>
            html, body { margin: 0; padding: 0; overflow:hidden; }
            body {
                background-color: black
            }

	</style>
        <div id="div" align="center">
            <canvas id="canvas" style="border: 0px">
                <p>Похоже, ваш браузер не поддерживает HTML5 и тег &lt;canvasas&gt;.</p>
                <p>Попробуйте использовать один из следующих браузеров:</p>
                <ul>
                 <li>Chrome (&gt;4.0)</li>
                 <li>Opera (&gt;9.0)</li>
                 <li>Opera Mobile (&gt;10.0)</li>
                 <li>Firefox (&gt;2.0)</li>
                 <li>Safari (&gt;3.1)</li>
                 <li>iOS Safari (&gt;3.2)</li>
                 <li>Android Browser (&gt;2.1)</li>
                 <li>Internet Explorer (&gt;=9.0)</li>
                </ul>
            </canvas>
        </div>        
        
        <script>
        var b = document.getElementById("div");
        var c = document.getElementById("canvas");
        var a = c.getContext("2d");
        var W=c.width=document.body.clientWidth; //ширина - по размерам клиенской части окна
        var H=c.height=screen.height; //высота - это не "во весь экран", а больше из-за служебных областей окна
        var Objects=[];
        var Colors="255,0,0;0,255,0;0,0,255;255,255,0;255,0,255;0,255,255;255,255,204;255,204,255;204,255,255".split(";");
        var timeInterval=20; //частота обновления, мс
        var petardColor="rgb(0,128,0)"; //цвет петарды до взрыва
        var numRays=16; //количество лучей <s>чучхе</s> при взрыве
        var percentAlive=60; //процент пускаемых, 0-все, 100-никто
        var petardRadius=3; //начальный радиус петарды, пикс.
        var fireRadius=31; //радиус для вызрыва, пикс.
        var fireBallRadius=5; //радиус отдельного огонька при взрыве, пикс.

        DeleteObject=function (obj,t) {
         if(t) delete Objects[obj];
         else Objects[Objects.length]=obj;
        };

        DrawBack=function() {
         a["globalCompositeOperation"]="source-over"; //новая фигура визуализируется поверх уже добавленных на холст
         a.fillStyle="rgba(0,0,0,.4)";
         a.fillRect(0,0,W,H);
        };

        ColorPath=function(x,y,r,f) {
         a.beginPath();
         a.arc(x,y,r,0,Math.PI*2,0);
         a.fillStyle=f;
         a.fill();
        };

        FinalDraw=function(k,x,y,g){
         this.k=k;
         this.x=k?x:(Math.random()*(W-200))+100;
         this.y=k?y:H;
         this.t=0;
         this.j=k ? 25 : 75;
         this.a=k ? Math.random()*360 : 240+Math.random()*70;
         this.s=Math.random()*3+2;
         this.g=g;

         this.thisDraw=function() {
          this.t++;
          if(this.k) { //взрыв
           f=(Math.PI/180)*this.a;
           this.x+=Math.cos(f)*this.s;
           this.y+=Math.sin(f)*this.s;
           a["globalCompositeOperation"]="lighter";
           g=a.createRadialGradient(this.x,this.y,1,this.x,this.y,fireBallRadius);
           g["addColorStop"](0,"rgba(255,255,255,.55)");
           g["addColorStop"](1,"rgba("+this.g+",.03)");
           ColorPath(this.x,this.y,fireRadius,g);
          }
          else { //пуск петарды
           f=(Math.PI/180)*this.a;
           this.x+=Math.cos(f)*5; //
           this.y+=Math.sin(f)*8; //увеличьте для взрывов выше
           ColorPath(this.x,this.y,petardRadius,petardColor);
          }
         }
        };

        setInterval(
         function() {
          DrawBack();
          for (q in Objects) {
           var obj=Objects[q];
           obj.thisDraw();
           if(obj.t>obj.j) {
            if(!obj.k) {
             h=Math.random()*Colors.length|0;
             for (c=0;c<numRays;c++) DeleteObject(new FinalDraw(1,obj.x,obj.y,Colors[h]));
            }
            DeleteObject(q,1);
           }
          }
          var c=Math.random()*100;
          if(c>percentAlive) DeleteObject(new FinalDraw);
         },timeInterval);

        </script>
    </body>
</html>
