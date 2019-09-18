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
            try
            {


                IMongoDatabase banco = DataSource.GetConnection();
                MongoClient client = banco.Client as MongoClient;

                if (client == null)
                {
                    throw new Exception("Client is Not Mongo Client");
                }

                //var javascript = "function getValueForNextSequence(sequenceOfName){"+
                //                        "var sequenceDoc = db.sample.findAndModify({"+
                //                                             "query: { _id: sequenceOfName },"+
                //                                            "update: {$inc: { sequence_value: 1} },"+
                //                                            " new:true});"+

                //                        "return sequenceDoc.sequence_value;" +
                //                "}";




                var javascript = "function() { return 'Hello, world!'; }";
                banco.RunCommand<Object>(javascript);



                //var function = new BsonJavaScript(javascript);
                //var op = new EvalOperation(banco.DatabaseNamespace, function, null);
                //using (var writeBinding = new WritableServerBinding(client.Cluster, new CoreSessionHandle(NoCoreSession.Instance)))
                //{
                //    await op.ExecuteAsync(writeBinding, CancellationToken.None);
                //};



                //IMongoDatabase banco = DataSource.GetConnection();
                //IMongoCollection<OrdemServico> collection = banco.GetCollection<OrdemServico>(typeof(OrdemServico).Name);

                //var ordem_servico = new OrdemServico
                //{
                //    Num_OS = 0,
                //    Empresa = empresa,
                //    Data_Agendamento = obj.Data_Agendamento,
                //    Data_Modificacao = DateTime.Now,
                //    Descricao_Problema = obj.Descricao_problema,
                //    Observacao_Produto = obj.Observacao_produto,
                //    Produto = obj.Produto,
                //    Tecnico_Resp = obj.Tecnico_Resp,
                //    Valor_Servico = obj.Valor_Servico,
                //    Status = obj.status,

                //};
                //await new Ordem_ServicoDAO().Insert(ordem_servico);

                //var update = Builders<OrdemServico>.Update.Inc("Num_OS", 1);
                //Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS.Equals(0);

                //await collection.UpdateManyAsync<OrdemServico>(filter, update);
            }
            catch (Exception ex)
            {
                throw new Exception("Erro: " + ex.ToString());
            }
        }



    }
}