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


        [HttpPost]
        [Route("api/OrdemServicoApiController/Cadastrar")]
        public async Task<PostApiResponse<bool>> Cadastrar(OrdemServicoCadastro values)
        {
            var retorno = new PostApiResponse<bool>();

            try
            {

                if (values.num_os == 0)
                {
                    retorno.Result = false;
                    retorno.Mensagem = "Numero da Ordem de Serviço não pode ser zero.";
                }

                else
                {


                    Expression<Func<Empresa, bool>> filter = x => x.cpfcnpj == values.cpfCnpj;
                    var empresa = new EmpresaDAO().FindFirstBywhere(filter);


                    Expression<Func<OrdemServico, bool>> filterOrdem = x => x.num_os.Equals(values.num_os) && x.Empresa == empresa._id;
                    var ordem = new Ordem_ServicoDAO().FindByWhere(filterOrdem);

                    if (ordem.Count() != 0)
                    {
                        retorno.Result = false;
                        retorno.Mensagem = "Ordem de Serviço ja cadastra com esse numero";
                    }
                    else
                    {

                        if (String.IsNullOrEmpty(values.cliente_responsavel.cpf_cnpj))
                        {
                            retorno.Result = false;
                            retorno.Mensagem = "CPF OU CNPJ do cliente  em branco.";
                        }

                        else
                        {

                            Expression<Func<ClienteModels, bool>> filterCliente = x => x.cpf_cnpj == values.cliente_responsavel.cpf_cnpj;
                            var cliente = new ClienteDAO().FindFirstBywhere(filterCliente);

                            if (cliente == null)
                            {
                                values.cliente_responsavel.Data_Modificacao = DateTime.Now.ToString();
                                await new ClienteDAO().Insert(values.cliente_responsavel);


                                cliente = new ClienteDAO().FindFirstBywhere(filterCliente);

                            }

                            var ordem_servico = new OrdemServico
                            {
                                num_os = values.num_os,
                                cliente_responsavel = cliente._id,
                                Empresa = empresa._id,
                                data_agendamento = values.data_agendamento,
                                Data_Modificacao = DateTime.Now.ToString(),
                                descricao_problema = values.descricao_problema,
                                //Observacao_Produto = values.Observacao_produto,
                                Produto = values.Produto,
                                tecnicoResp = values.tecnicoResp,
                                //Valor_Servico = values.Valor_Servico,
                                status_os = values.status_os,

                            };
                            await new Ordem_ServicoDAO().Insert(ordem_servico);

                            retorno.Result = true;
                            retorno.Mensagem = "Ordem de Serviço cadastrada com sucesso.";



                        }

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
        [Route("api/OrdemServicoApiController/GetOrdemServicoEmpresa")]
        public PostApiResponse<List<OrdemServicoModel>> GetOrdemServicoEmpresa(String cpf_cnpj)
        {
            var retorno = new PostApiResponse<List<OrdemServicoModel>>();

            try
            {
                if (String.IsNullOrEmpty(cpf_cnpj))
                {
                    retorno.Result = false;
                    retorno.Mensagem = "CNPJ ou CPF da Empresa não informado";
                }
                else
                {

                    Expression<Func<Empresa, bool>> filterEmpresa = x => x.cpfcnpj == cpf_cnpj;
                    var empresa = new EmpresaDAO().FindFirstBywhere(filterEmpresa);

                    if (empresa == null)
                    {
                        retorno.Result = false;
                        retorno.Mensagem = "Empresa não encontrada";
                    }
                    else
                    {

                        var lista =  new Ordem_ServicoDAO().GetAll(empresa._id);
                                             
                        
                        retorno.Result = true;
                        retorno.Objeto = lista;
                       

                    }
                }
            }
            catch (Exception ex)
            {
                retorno.Result = false;
                retorno.Exception = ex.Message;
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

                    OrdemServico ordem = null;

                    Expression<Func<Empresa, bool>> filterEmpresa = x => x.cpfcnpj == CPFCNPJ;
                    var empresa = new EmpresaDAO().FindFirstBywhere(filterEmpresa);

                    if (empresa != null)
                    {
                        Expression<Func<OrdemServico, bool>> filterOrdem = x => x.num_os == Num_OS && x.Empresa == empresa._id;
                        ordem = new Ordem_ServicoDAO().FindFirstBywhere(filterOrdem);
                    }
                    else
                    {
                        Expression<Func<ClienteModels, bool>> filterCliente = x => x.cpf_cnpj == CPFCNPJ;
                        var cliente = new ClienteDAO().FindFirstBywhere(filterCliente);
                        if (cliente != null)
                        {
                            Expression<Func<OrdemServico, bool>> filterOrdem = x => x.num_os == Num_OS && x.cliente_responsavel == cliente._id;
                            ordem = new Ordem_ServicoDAO().FindFirstBywhere(filterOrdem);
                        }
                    }


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
        public async Task<PostApiResponse<bool>> EditarOrdemServico(OrdemServicoCadastro values)
        {
            PostApiResponse<bool> resposta = new PostApiResponse<bool>();
            try
            {

                if (values.num_os == 0 || values.num_os == null)
                {
                    resposta.Result = false;
                    resposta.Mensagem = "Numero da OS em Branco.";
                }
                else if (String.IsNullOrEmpty(values.cpfCnpj))
                {
                    resposta.Result = false;
                    resposta.Mensagem = "CPF ou CNPJ não informando";
                }
                else
                {
                    Expression<Func<Empresa, bool>> filter2 = x => x.cpfcnpj == values.cpfCnpj;
                    var EmpresaEncontrada = new EmpresaDAO().FindFirstBywhere(filter2);

                    Expression<Func<OrdemServico, bool>> filter = x => x.num_os == values.num_os && x.Empresa == EmpresaEncontrada._id;
                    var Ordem_Atualizada = new Ordem_ServicoDAO().FindFirstBywhere(filter);

                    if (Ordem_Atualizada != null)
                    {

                        

                        if (EmpresaEncontrada != null)
                        {

                            Ordem_Atualizada.data_agendamento = values.data_agendamento;
                            Ordem_Atualizada.Data_Modificacao = DateTime.Now.ToString();
                            Ordem_Atualizada.descricao_problema = values.descricao_problema;
                            Ordem_Atualizada.Empresa = EmpresaEncontrada._id;
                            Ordem_Atualizada.observacao_produto = values.observacao_produto;
                            Ordem_Atualizada.Produto = values.Produto;
                            Ordem_Atualizada.tecnicoResp = values.tecnicoResp;
                            Ordem_Atualizada.valor_servico = values.valor_servico;
                            Ordem_Atualizada.status_os = values.status_os;

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
                    Expression<Func<Empresa, bool>> filter = x => x.cpfcnpj == values.CPFCNPJ_Empresa;
                    var empresa_encontrada = new EmpresaDAO().FindFirstBywhere(filter);

                    Expression<Func<OrdemServico, bool>> filterOrdem = x => x.num_os == values.Num_OS && x.Empresa == empresa_encontrada._id;
                    var ordem_encontra = new Ordem_ServicoDAO().FindFirstBywhere(filterOrdem);

                    if (ordem_encontra != null)
                    {
                        ordem_encontra.status_os = values.status;
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
                    Expression<Func<Empresa, bool>> filterEmpresa = x => x.cpfcnpj == CPFCNPJ;
                    var empresa = new EmpresaDAO().FindFirstBywhere(filterEmpresa);

                    Expression<Func<OrdemServico, bool>> filter = x => x.num_os == Num_OS && x.Empresa == empresa._id;
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