using AppOSWebApi.Models;
using MongoDB.Bson;
using MongoDB.Driver;
using MongoDB.Driver.Core.Bindings;
using MongoDB.Driver.Core.Operations;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading;
using System.Threading.Tasks;
using System.Web;

namespace AppOSWebApi.Persistence.DAO
{
    public class Ordem_ServicoDAO : BaseDAO<OrdemServico>
    {


        public async Task InsertSequence(OrdemServicoCadastro obj,Empresa empresa)
        {
           
        }



    }
}