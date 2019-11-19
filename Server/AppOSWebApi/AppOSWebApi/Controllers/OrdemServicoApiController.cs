using AppOSWebApi.DAO;
using AppOSWebApi.Models;
using AppOSWebApi.Persistence.DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http;

namespace AppOSWebApi.Controllers
{
    public class OrdemServicoApiController : ApiController
    {




        [HttpGet]
        [Route("api/OrdemServicoApiController/GetCliente")]
        public async Task<PostApiResponse<ClienteModels>> GetCliente(String cpf_cnpj) {
            var retorno = new PostApiResponse<ClienteModels>();
            try
            {
                Expression<Func<OrdemServico, bool>> filter = x => x.ClienteResp.cpf_cnpj == cpf_cnpj;
                var cliente = new Ordem_ServicoDAO().FindFirstBywhere(filter);

                if (cliente == null)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Cliente não Cadastrado";
                }
                else
                {
                    retorno.Result = true;
                    retorno.Mensagem = "Cliente localizado";
                    retorno.Objeto = cliente.ClienteResp;
                }
                
            }
            catch (Exception ex) {
                retorno.Result = false;
                retorno.Exception = ex.Message;
            }
            return retorno;
        }

        [HttpPost]
        [Route("api/OrdemServicoApiController/Cadastrar")]
        public async Task<PostApiResponse<bool>> Cadastrar(OrdemServicoCadastro values)
        {
            var retorno = new PostApiResponse<bool>();

            try
            {

                if (values.NumOS == 0)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Numero da Ordem de Serviço não pode ser zero.";
                }
                
                else
                {


                    Expression<Func<Empresa, bool>> filter = x => x.CPFCNPJ == values.cpfCnpj;
                    var empresa = new EmpresaDAO().FindFirstBywhere(filter);


                    Expression<Func<OrdemServico, bool>> filterOrdem = x => x.Num_OS.Equals(values.NumOS) && x.Empresa.CPFCNPJ.Equals(empresa.CPFCNPJ);
                    var ordem = new Ordem_ServicoDAO().FindByWhere(filterOrdem);

                    if (ordem.Count() != 0)
                    {
                        retorno.Result = false;
                        retorno.Mensagem = "Ordem de Serviço ja cadastra com esse numero";
                    }
                    else
                    {

                        var ordem_servico = new OrdemServico
                        {
                            Num_OS = values.NumOS,
                            ClienteResp = values.cliente_responsavel,
                            Empresa = empresa,
                            Data_Agendamento = values.Data_Agendamento,
                            Data_Modificacao = DateTime.Now,
                            Descricao_Problema = values.Descricao_problema,
                            Observacao_Produto = values.Observacao_produto,
                            Produto = values.Produto,
                            Tecnico_Resp = values.tecnicoResp,
                            Valor_Servico = values.Valor_Servico,
                            Status = values.status_os,

                        };
                        await new Ordem_ServicoDAO().Insert(ordem_servico);

                        retorno.Result = true;
                        retorno.Mensagem = "Ordem de Serviço cadastrada com sucesso.";
                    }
                }
            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.ToString();
            }

            return retorno;
        }




        [HttpGet]
        [Route("api/OrdemServicoApiController/GetOrdemServico")]
        public PostApiResponse<OrdemServico> GetOrdemServico(Int64? Num_OS, String CPFCNPJ)
        {
            PostApiResponse<OrdemServico> resposta = new PostApiResponse<OrdemServico>();
            try
            {
                if (Num_OS == 0 || Num_OS == null)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Numero de OS não pode ficar em branco.";
                }
                else if (String.IsNullOrEmpty(CPFCNPJ))
                {
                    resposta.Result = false;
                    resposta.Mensagem = "CPF ou CNPJ não informado.";

                }
                else
                {
                    Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS == Num_OS && x.Empresa.CPFCNPJ == CPFCNPJ ||x.Num_OS == Num_OS && x.ClienteResp.cpf_cnpj == CPFCNPJ;
                    var ordem = new Ordem_ServicoDAO().FindFirstBywhere(filter);
                    if (ordem == null)
                    {
                        resposta.Result = false;
                        resposta.Mensagem = "Ordem de Serviço não encontrada! \n Favor verifique os dados informados";
                    }
                    else
                    {
                        resposta.Result = true;
                        resposta.Mensagem = "Ordem de Serviço Encontrada";
                        resposta.Objeto = ordem;
                    }
                    
                }
                
            }
            catch (Exception ex)
            {
                resposta.Result = false;
                resposta.Exception = ex.Message;
            }

            return resposta;
        }

        [HttpPost]
        [Route("api/OrdemServicoApiController/EditarOrdemServico")]
        public async Task<PostApiResponse<bool>> EditarOrdemServico(OrdemServico values)
        {
            PostApiResponse<bool> resposta = new PostApiResponse<bool>();
            try
            {

                if (values.Num_OS == 0 || values.Num_OS == null)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Numero da OS em Branco.";
                }
                else if (String.IsNullOrEmpty(values.Empresa.CPFCNPJ))
                {
                    resposta.Result = false;
                    resposta.Mensagem = "CPF ou CNPJ não informando";
                }
                else
                {

                    Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS == values.Num_OS && x.Empresa.CPFCNPJ == values.Empresa.CPFCNPJ;
                    var Ordem_Atualizada = new Ordem_ServicoDAO().FindFirstBywhere(filter);

                    if (Ordem_Atualizada != null)
                    {

                        Expression<Func<Empresa, bool>> filter2 = x => x.CPFCNPJ == Ordem_Atualizada.Empresa.CPFCNPJ;
                        var EmpresaEncontrada = new EmpresaDAO().FindFirstBywhere(filter2);

                        if (EmpresaEncontrada != null)
                        {

                            Ordem_Atualizada.Data_Agendamento = values.Data_Agendamento;
                            Ordem_Atualizada.Data_Modificacao = DateTime.Now;
                            Ordem_Atualizada.Descricao_Problema = values.Descricao_Problema;
                            Ordem_Atualizada.Empresa = EmpresaEncontrada;
                            Ordem_Atualizada.Observacao_Produto = values.Observacao_Produto;
                            Ordem_Atualizada.Produto = values.Produto;
                            Ordem_Atualizada.Tecnico_Resp = values.Tecnico_Resp;
                            Ordem_Atualizada.Valor_Servico = values.Valor_Servico;

                            await new Ordem_ServicoDAO().Update(Ordem_Atualizada);

                            resposta.Result = true;
                            resposta.Mensagem = "Ordem de Serviço Atualizada com sucesso!";
                        }
                        else
                        {
                            resposta.Result = false;
                            resposta.Mensagem = "Ordem de Serviço não encontrada";
                        }
                    }
                    else
                    {
                        resposta.Result = false;
                        resposta.Mensagem = "Ordem de Serviço não encontrada";
                    }
                }
            }
            catch (Exception ex)
            {
                resposta.Result = false;
                resposta.Exception = ex.ToString();
            }

            return resposta;
        }




        [HttpPost]
        [Route("api/OrdemServicoApiController/AtualizarStatusOrdemServico")]
        public async Task<PostApiResponse<bool>> AtualizarStatusOrdemServico(OrdemServicoParam values)
        {
            PostApiResponse<bool> resposta = new PostApiResponse<bool>();
            try
            {
                if (String.IsNullOrEmpty(values.CPFCNPJ_Empresa))
                {
                    resposta.Result = false;
                    resposta.Mensagem = "CPF ou CNPJ não informado";
                }
                else if (values.Num_OS == 0 || values.Num_OS == null)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Número da OS não informado";
                }
                else if (values.status == null)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Status não informado";
                }
                else
                {
                    Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS == values.Num_OS && x.Empresa.CPFCNPJ == values.CPFCNPJ_Empresa;
                    var ordem_encontra = new Ordem_ServicoDAO().FindFirstBywhere(filter);
                    if (ordem_encontra != null)
                    {
                        ordem_encontra.Status = values.status;
                        await new Ordem_ServicoDAO().Update(ordem_encontra);
                        resposta.Result = true;
                        resposta.Mensagem = "Status Atualizado com sucesos ";
                    }
                    else
                    {
                        resposta.Result = false;
                        resposta.Mensagem = "Ordem de Serviço não encontrada";

                    }
                }
            }
            catch (Exception ex)
            {
                resposta.Result = false;
                resposta.Exception = ex.Message;
            }

            return resposta;
        }



        [HttpPost]
        [Route("api/OrdemServicoApiController/ExcluirOrdem")]
        public async Task<PostApiResponse<bool>> ExcluirOrdem(Int64 Num_OS, String CPFCNPJ)
        {
            PostApiResponse<bool> resposta = new PostApiResponse<bool>();
            try
            {
                if (Num_OS == 0)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Numero da OS não informada!";

                }
                else if (String.IsNullOrEmpty(CPFCNPJ))
                {
                    resposta.Result = false;
                    resposta.Mensagem = "CPF ou CNPJ da empresa não informado";

                }
                else
                {
                    Expression<Func<OrdemServico, bool>> filter = x => x.Num_OS == Num_OS && x.Empresa.CPFCNPJ == CPFCNPJ;
                    var ordem_encontrada = new Ordem_ServicoDAO().FindFirstBywhere(filter);
                    if (ordem_encontrada != null)
                    {
                        await new Ordem_ServicoDAO().Delete(ordem_encontrada);

                        resposta.Result = true;
                        resposta.Mensagem = "Ordem de Serviço deletada com sucesso!";
                    }
                    else
                    {
                        resposta.Result = false;
                        resposta.Mensagem = "Ordem de Serviço não encontrada";
                    }
                }
            }
            catch (Exception ex)
            {
                resposta.Result = false;
                resposta.Exception = ex.Message;
            }

            return resposta;
        }




    }
}