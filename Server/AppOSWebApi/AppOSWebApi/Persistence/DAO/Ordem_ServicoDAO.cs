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

        public List<OrdemServicoModel> GetAll(ObjectId Empresa)
        {

            IMongoDatabase banco = DataSource.GetConnection();
            IMongoCollection<OrdemServico> collection = banco.GetCollection<OrdemServico>(typeof(OrdemServico).Name);

            var match = new BsonDocument(
                "Empresa", Empresa
            );

            var lista = collection.Aggregate().Lookup(typeof(ClienteModels).Name, "cliente_responsavel", "_id", "Cliente").Project("{num_os:1,Produto:1,descricao_problema:1,observacao_produto:1, tecnicoResp:1,status_os:1, Empresa:1, valor_servico: 1,Cliente:{$arrayElemAt:['$Cliente',0]}}").Match(match).ToListAsync().Result;
            var listaOrdemServico = new List<OrdemServicoModel>();
            foreach (var itens in lista)
            {
                listaOrdemServico.Add(new OrdemServicoModel
                {
                    num_os = itens["num_os"].ToInt64(),
                    descricao_problema = itens["descricao_problema"].ToString(),
                    observacao_produto = itens["observacao_produto"].ToString(),
                    status_os = itens["status_os"].ToInt32(),
                    tecnicoResp = itens["tecnicoResp"].ToString(),
                    valor_servico = Convert.ToDouble(itens["valor_servico"]),
                    cliente_responsavel = new ClienteModels
                    {
                        cpf_cnpj = itens["Cliente"]["cpf_cnpj"].ToString(),
                        nome = itens["Cliente"]["nome"].ToString(),
                        email = itens["Cliente"]["email"].ToString(),
                        telefone = itens["Cliente"]["telefone"].ToString(),
                        endereco = new EnderecoModels
                        {
                            cep = itens["Cliente"]["endereco"]["cep"].ToString(),
                            Logradouro = itens["Cliente"]["endereco"]["Logradouro"].ToString(),
                            uf = itens["Cliente"]["endereco"]["uf"].ToString(),
                            Localidade = itens["Cliente"]["endereco"]["Localidade"].ToString(),
                            Bairro = itens["Cliente"]["endereco"]["Bairro"].ToString(),
                            num_residencia = itens["Cliente"]["endereco"]["num_residencia"].ToString(),
                            Complemento = itens["Cliente"]["endereco"]["Complemento"].ToString()
                        }
                    },
                    Produto = new ProdutosModels
                    {
                        Descricao = itens["Produto"]["Descricao"].ToString()
                    },

                });
            }

            return listaOrdemServico;

        }




    }
}