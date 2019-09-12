using AppOSWebApi.Core;
using AppOSWebApi.Models;
using MongoDB.Bson;
using MongoDB.Bson.Serialization;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace AppOSWebApi.DAO
{
    public class EmpresaDAO : Database
    {

        private IMongoCollection<BsonDocument> Collection;

        public EmpresaDAO()
        {
            var db = GetConexao();
            Collection = db.GetCollection<BsonDocument>("Empresa");

        }

        public void Insert(EmpresaModels empresa)
        {
            var document = BsonDocument.Create(empresa);
            Collection.InsertOne(document);

        }


        public List<EmpresaModels> GetAll()
        {
            var documents = Collection.Find(new BsonDocument()).ToBson();
            return BsonSerializer.Deserialize<List<EmpresaModels>>(documents);
        }

    }
}