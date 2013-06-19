using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using MySql.Data;
using MySql.Data.MySqlClient;

namespace WebApplication1
{
    /// <summary>
    /// Summary description for service_mysql
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]

    public class service_mysql : System.Web.Services.WebService
    {
        String connStr = "Server=techniek.server-ict.nl:13306;Database=gamepad;Uid=user;Pwd=kaas";

        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }
    }
}
