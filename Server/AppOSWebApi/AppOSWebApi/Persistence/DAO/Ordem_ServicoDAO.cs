using AppOSWebApi.Models;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
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
                IMongoCollection<OrdemServico> collection = banco.GetCollection<OrdemServico>(typeof(OrdemServico).Name);

                var ordem_servico = new OrdemServico
                {
                    Num_OS = 0,
                    Empresa = empresa,
                    Data_Agendamento = obj.Data_Agendamento,
                    Data_Modificacao = DateTime.Now,
                    Descricao_Problema = obj.Descricao_problema,
                    Observacao_Produto = obj.Observacao_produto,
                    Produto = obj.Produto,
                    Tecnico_Resp = obj.Tecnico_Resp,
                    Valor_Servico = obj.Valor_Servico,
                    Status = obj.status,

                };

                await new Ordem_ServicoDAO().Insert(ordem_servico);

                var update = Builders<OrdemServico>.Update.Inc("Num_OS", 1);
                Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS.Equals(0);

                await collection.UpdateManyAsync<OrdemServico>(filter, update);
            }
            catch (Exception ex)
            {
                throw new Exception("Erro: " + ex.ToString());
            }
        }



    }
}