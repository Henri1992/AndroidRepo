﻿using System;
using System.Collections.Generic;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebApplication1
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]

    public class service : System.Web.Services.WebService
    {
        public service()
        {
            //Uncomment the following line if using designed components 
            //InitializeComponent(); 
        }



        [WebMethod]
        public string diceGet()
        {
            TextReader tr = new StreamReader("c:/inetpub/wwwroot/worp.txt");
            String worp = tr.ReadLine();
            tr.Close();
            return worp;

        }

        [WebMethod]
        public String diceSet(int player, int worp, int pion, int succesvol)// ,int gameID)
        {
            TextWriter tw = new StreamWriter("c:/inetpub/wwwroot/worp.txt");
            String _string = player + "," + worp + "," + pion + "," + succesvol;// + "," + gameID;
            tw.WriteLine(_string);
            tw.Close();
            return _string;
        }

        [WebMethod]
        public int finishGet(int playerID)
        {
            using (StreamReader sr = new StreamReader("c:/inetpub/wwwroot/finish.txt"))
            {
                while(!sr.EndOfStream)
                {
                    if (int.Parse(sr.ReadLine()) == playerID)
                    {
                        return playerID;
                    }
                }
            }

            // Wanneer playerID niet is gevonden return 0
            return 0;
        }

        [WebMethod]
        public int finishSet(int playerID)
        {
            File.AppendAllText("c:/inetpub/wwwroot/finish.txt", playerID + "\r\n");
            return playerID;
        }

        [WebMethod]
        public int gameStartGet(String playerID)
        {
            TextReader tr = new StreamReader("c:/inetpub/wwwroot/gameStart.txt");
            String _string = tr.ReadLine();
            tr.Close();
            // Check of string niet leeg is, anders geef 0
            if (_string == null)
            {
                return 0;
            }

            String[] lineSplit = _string.Split(';');
            if (int.Parse(lineSplit[0]) == 1 && lineSplit.Length == 2)
            {
                String[] playerSplit = lineSplit[1].Split(',');
                if (playerSplit.Contains(playerID) && !String.IsNullOrEmpty(playerID))
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
            else
            {
                return 0;
            }

        }

        [WebMethod]
        public int gameStartSet(int status, String spelers)
        {


            if (status == 1)
            {
                TextWriter tw = new StreamWriter("c:/inetpub/wwwroot/gameStart.txt");
                String _string = status.ToString() + ";" + spelers;
                tw.WriteLine(_string);
                tw.Close();
                return status;
            }
            else
            {
                TextWriter tw = new StreamWriter("c:/inetpub/wwwroot/gameStart.txt");
                tw.WriteLine(0);
                tw.Close();
                return 0;
            }
        }


        [WebMethod]
        public int newGameGet()
        {
            TextReader tr = new StreamReader("c:/inetpub/wwwroot/gameStatus.txt");
            int game = Convert.ToInt32(tr.ReadLine());
            tr.Close();
            return game;
        }

        private void reset()
        {
            StreamWriter swGameStart = new StreamWriter("c:/inetpub/wwwroot/gameStart.txt");
            swGameStart.Close();

            StreamWriter swPion = new StreamWriter("c:/inetpub/wwwroot/pion.txt");
            swPion.Close();

            StreamWriter swPlayer = new StreamWriter("c:/inetpub/wwwroot/player.txt");
            swPlayer.Close();

            StreamWriter swFinish = new StreamWriter("c:/inetpub/wwwroot/finish.txt");
            swFinish.Close();

            // Schrijf 0 naar turnSet zodat telefoon niet niks terugkrijgt
            // Doe dit via de methode omdat deze ook op het type int controleert
            turnSet(0);
        }

        [WebMethod]
        public int newGameSet(int game)
        {
            reset();

            TextWriter tw = new StreamWriter("c:/inetpub/wwwroot/gameStatus.txt");
            tw.WriteLine(game);
            tw.Close();

            return game;
        }


        [WebMethod]
        public string pionGet(int playerID)
        {
            TextReader tr = new StreamReader("c:/inetpub/wwwroot/pion.txt");
            string pion = tr.ReadLine();
            tr.Close();
            return pion;
        }

        [WebMethod]
        public int pionSet(int playerID, int pion, int status)//, int gameID)
        {
            File.AppendAllText("c:/inetpub/wwwroot/pion.txt", "\r\n" + playerID + "," + pion + "," + status); // + "," + gameID);
            return playerID;
        }


        [WebMethod]
        public String playerGet(String playerIDAndDateTime)
        {
            String _string = String.Empty;
            String[] lines = File.ReadAllLines("c:/inetpub/wwwroot/player.txt");
            // Declareer lineSplit variabele
            String[] lineSplit;

            if (!String.IsNullOrEmpty(playerIDAndDateTime))
            {
                // Probeer dateTime van playerIDAndDateTime te parsen
                // en sla dan deze op in dateTimeLast anders niks teruggeven
                lineSplit = playerIDAndDateTime.Split(',');
                DateTime dateTimeLast;
                if (lineSplit.Length == 1)
                {
                    lineSplit = new string[] { lineSplit[0], String.Empty };
                }
                // Parse dateTime
                // Faalt parsen, stuur dan niks terug
                if (!DateTime.TryParse(lineSplit[1], out dateTimeLast))
                {
                    return _string;
                }


                // Haal dateTime van laatst toegevoegde lijn op
                lineSplit = lines[lines.Length - 1].Split(',');
                // Declaratie en initialisatie van dateTimeLine
                DateTime dateTimeLine = DateTime.Parse(lineSplit[1]);

                // Beëindig methode als blijkt dat er geen nieuwe gegevens beschikbaar zijn
                TimeSpan timeSpanLine = dateTimeLast - dateTimeLine;
                if (timeSpanLine.TotalMilliseconds > 0)
                {
                    if (String.IsNullOrEmpty(_string))
                    {
                        _string = "nothing";
                    }
                    return _string;
                }
            }
            else
            {
                if (lines.Length == 0)
                {
                    _string = "nothing";
                    return _string;
                }
            }
                

            int linesToGo = lines.Length - 1;
            while (linesToGo >= 0)
            {
                lineSplit = lines[linesToGo].Split(',');

                if (!String.IsNullOrEmpty(playerIDAndDateTime) && playerIDAndDateTime.Equals(lines[linesToGo]))
                {
                    break;
                }

                _string += lineSplit[0] + "," + lineSplit[1] + ";";
                linesToGo--;
            }

            if (String.IsNullOrEmpty(_string))
            {
                _string = "nothing";
            }
            return _string;
        }

        [WebMethod]
        public string playerSet()
        {
            string[] lines = File.ReadAllLines("c:/inetpub/wwwroot/player.txt");

            // Declaratie en initialisatie van playerString
            String playerString = String.Empty;

            DateTime dateTimeNow = new DateTime();
            dateTimeNow = DateTime.Now;

            using (StreamWriter sw = new StreamWriter("c:/inetpub/wwwroot/player.txt"))
            {
                if (lines.Length != 0)
                {
                    for (int i = 0; i < lines.Length; i++)
                    {
                        sw.WriteLine(lines[i]);
                    }

                    String[] lineSplit = lines[lines.Length - 1].Split(',');
                    playerString = (int.Parse(lineSplit[0]) + 1).ToString();
                    String lastLine = playerString + "," + dateTimeNow.ToString();
                    sw.WriteLine(lastLine);
                }
                else
                {
                    playerString = (1).ToString();
                    String lastLine = playerString + "," + dateTimeNow.ToString();
                    sw.WriteLine(lastLine);

                }
            }

            return playerString;
        }


        [WebMethod]
        public string turnGet()
        {
            TextReader tr = new StreamReader("c:/inetpub/wwwroot/turn.txt");
            string turn = tr.ReadLine();
            tr.Close();
            return turn;
        }

        [WebMethod]
        public int turnSet(int playerID)//, int gameID)
        {
            TextWriter tw = new StreamWriter("c:/inetpub/wwwroot/turn.txt");
            tw.WriteLine(playerID + "," + DateTime.Now.Ticks);// + "," + gameID);
            tw.Close();
            return playerID;
        }

    }
}
