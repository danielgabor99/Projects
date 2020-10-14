using lab9.Models;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace lab9.Data
{
    public class DAL
    {
        public List<Multimedia> GetMultimediaFormat(string format)
        {
            NpgsqlConnection conn;

            string connectionString = String.Format("Server={0};Port={1};" +
                "User Id={2};Password={3};Database={4}",
                "localhost", 5432, "postgres",
                "postgres", "mydbweb");


            List<Multimedia> slist = new List<Multimedia>();
            conn = new NpgsqlConnection(connectionString);
            conn.Open();

           
                

                NpgsqlCommand cmd = new NpgsqlCommand();
                cmd.Connection = conn;
                cmd.CommandText = "select * from multimedia";
                NpgsqlDataReader myreader = cmd.ExecuteReader();

                while (myreader.Read())
                {
                    Multimedia m = new Multimedia();
                    m.title = myreader.GetString(0);
                    m.format = myreader.GetString(1);
                    m.genre = myreader.GetString(2);
                   
                    m.path = myreader.GetString(3);
                    slist.Add(m);
                }
             myreader.Close();
                 
          
            return slist;

        }
    }
}