using lab9.Data;
using lab9.Models;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace lab9.Controllers
{
    public class MainController : Controller
    {
        // GET: Main
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult Create()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Create(Multimedia multimedia)
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
            cmd.CommandText = "insert into multimedia values('"+multimedia.title+"','"+multimedia.format + "','" + multimedia.genre + "','" + multimedia.path+"')";
            return RedirectToAction("Index");
        }
        public string GetMultimediaFilter()
        {
            //string group_id = Request.Params["group_id"];
            DAL dal = new DAL();
            
            List<Multimedia> slist = dal.GetMultimediaFormat("nothing");
            ViewData["studentList"] = slist;

            string result = "<table><thead><th>Title</th><th>Format</th><th>Genre</th><th>Path</th></thead><style>table, th, td {border: 1px solid black;border - collapse: collapse;}</style>";
           
            foreach (Multimedia stud in slist)
            {
                result += "<tr><td>" + stud.title + "</td><td>" + stud.format + "</td><td>" + stud.genre + "</td><td>" + stud.path + "</td><td></tr>";
            }

            result += "</table>";
            return result;
        }
    }
}