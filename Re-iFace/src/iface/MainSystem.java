package iface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainSystem {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int communityid = -1;
		boolean loggedin = false,newuser = false;
		String error = null,option = null,optionnewentry = null,option1 = null,username = null, trash = null;
		Map<String,User> users = new HashMap<String,User>();
		Map<Integer,Community> systemcommunities = new HashMap<Integer,Community>();
		while(true) {
			System.out.println("Entrar(1)\n\nCriar Conta(2)\n\nFechar(0)");
			option = input.nextLine();
			if(option.equals("1")) {
				String password;
				while(!loggedin) {
					System.out.println("Informe seu Username: ");
					username = input.nextLine();
					System.out.println("Informe sua Senha: ");
					password = input.nextLine();
					if(users.containsKey(username)) {
						if(users.get(username).getPassword().equals(password)) loggedin = true;
						else {
							System.out.println("Senha incorreta. Deseja tentar novamente(1) ou Voltar(2)");
							error = input.nextLine();
							if(error.equals("2")) break;
						}
					}
					else {
						System.out.println("Username incorreto. Deseja tentar novamente(1) ou Voltar(2)");
						error = input.nextLine();
						if(error.equals("2")) break;
					}
				}
				
			}
			if(option.equals("2")) {
				String password,name;
				while(!loggedin) {
					System.out.println("Informe seu novo username(login): ");
					username = input.nextLine();
					System.out.println("Informe sua nova senha: ");
					password = input.nextLine();
					System.out.println("Informe seu nome: ");
					name = input.nextLine();
					if(users.containsKey(username)) {
						System.out.println("Username ja existe. Tente novamente.");
					}
					else {
						loggedin = true;
						newuser = true;
						User.NewUser(users, username, name, password);
						System.out.println("Conta criada com sucesso.");
					}
				}
			}
			else if(option.equals("0")) {
				System.out.println("Fim!");
				break;
			}
			if(loggedin) {
				System.out.println("\nSeja Bem Vindo/a: " + users.get(username).getName() + "\n");
				while(true) {
					if(newuser) {
						System.out.println("Deseja configurar seu Perfil agora? Se sim, pressione 1. Caso contrario, pressione 2");
						optionnewentry = input.nextLine();
						if(optionnewentry.equals("1")) option1 = "1";
						newuser = false;
					}
					if(optionnewentry.equals("2")) {
						System.out.println("\nOpcoes :\n\nCriar/Editar Perfil(1)\n\nSeus Amigos(2)\n\nAdicionar Amigo(3)\n\nRemover Amigo(4)\n\nSuas Solicitacoes de Amizade(5)\n\nEnviar Mensagem(6)\n\nSuas Mensagens(7)\n\nCrie uma Comunidade(8)\n\nSuas comunidades(9)\n\nTodas as Comunidades(10)\n\nInformacoes de Usuario(11)\n\nExcluir Minha Conta(nao pode ser desfeito)(12)\n\nSair(0)");
						option1 = input.nextLine();
					}
					//Try
					try {
						option1.equals("1");
					}
					catch(NullPointerException e) {
						option1 = "100";
					}
					//Option 1
					if(option1.equals("1")) {
						User.EditProfile(users, systemcommunities,username,input,communityid);
						optionnewentry = "2";
					}
					//Option 2
					else if(option1.equals("2")) {
						String current;
						if(users.get(username).getNumberOfFriends() > 0) {
							System.out.println("Amigos: ");
							for(int i = 0;i < users.get(username).getNumberOfFriends();i++) {
								current = users.get(username).getMyFriends().get(i);
								System.out.println(users.get(current).getName() + "(" + current +")");
							}
						}
						else System.out.println("Nao ha amigos.");
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 3
					else if(option1.equals("3")) {
						String friendusername;
						User friend = null;
						boolean canadd = false;
						System.out.println("Informe o Username do Amigo que deseja adicionar: ");
						friendusername = input.nextLine();
						if(users.containsKey(friendusername)) {
							friend = users.get(friendusername);
							canadd = true;
						}
						else System.out.println("Usuario nao existe.");
						if(canadd) {
							if(users.get(username).getMyFriends().contains(friendusername)) {
								System.out.println("Usuario ja e seu amigo");
							}
							else
							{
								friend.AddRequests(username,friendusername);
								System.out.println("Solicitacao enviada a: " + friend.getName());
							}
						}
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 4
					else if(option1.equals("4")) {
						String friendusername;
						User friend = null;
						boolean canremove = false;
						System.out.println("Informe o Username do Amigo que deseja remover: ");
						friendusername = input.nextLine();
						if(users.containsKey(friendusername)) {
							friend = users.get(friendusername);
							canremove = true;
						}
						else System.out.println("Usuario nao existe.");
						if(canremove) {
							if(users.get(username).getMyFriends().contains(friendusername)) {
								users.get(username).getMyFriends().remove(friendusername);
								friend.getMyFriends().remove(username);
								users.get(username).setNumberOfFriends(-1);
								friend.setNumberOfFriends(-1);
								System.out.println(friend.getName() + "(" + friend.getUsername() + ")" + " foi removido.");
							}
							else System.out.println("Usuario nao e seu amigo");
						}
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 5
					else if(option1.equals("5")) {
						String friendrequests,usernameofsolicitor;
						boolean isfriend = false;
						int arraysize = users.get(username).getMyRequests().size();
						if(arraysize > 0) {
							for(int i = 0;i < arraysize;i++) {
								usernameofsolicitor = users.get(username).getMyRequests().get(i).getSolicitor();
								if(users.containsKey(usernameofsolicitor)) {
									System.out.println(users.get(usernameofsolicitor).getName() + " deseja adicionar voce. Para confirmar pressione 1, para rejeitar pressione 2");
									friendrequests = input.nextLine();
									if(friendrequests.equals("1")) {
										if(users.get(username).getMyFriends().contains(usernameofsolicitor)) {
											System.out.println("Usuario ja e seu amigo.");
											isfriend = true;
										}
										if(!isfriend) {
											users.get(username).AddFriends(usernameofsolicitor);
											users.get(usernameofsolicitor).AddFriends(username);
											users.get(username).setNumberOfFriends(1);
											users.get(usernameofsolicitor).setNumberOfFriends(1);
											System.out.println(users.get(usernameofsolicitor).getName() + " adicionado\n");
											users.get(username).getMyRequests().remove(i);
										}
									}
									else if(friendrequests.equals("2")) {
										users.get(username).getMyRequests().remove(i);
										System.out.println(users.get(usernameofsolicitor).getName() + " rejeitado\n");
									}
								}
								else System.out.println("Nao foi possivel achar o Usuario da Solicitacao.");
								
							}
						}
						else System.out.println("Nao ha solicitacoes.");
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 6
					else if(option1.equals("6")) {
						String receiverusername,message;
						System.out.println("Informe o Username do Usuario que deseja mandar a mensagem: ");
						receiverusername = input.nextLine();
						if(users.containsKey(receiverusername)) {
							System.out.println("Sua mensagem: ");
							message = input.nextLine();
							users.get(receiverusername).NewMessage(message,username,receiverusername);
							users.get(username).NewMessage(message, username, receiverusername);
							users.get(receiverusername).InMyMessages(username);
							System.out.println("Mensagem enviada a " + users.get(receiverusername).getName());
						}
						else System.out.println("Nao foi possivel achar o Usuario.");
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 7
					else if(option1.equals("7")) {
						String seemessage,ownerofmessage,current;
						if(users.get(username).getMyMessages().size() > 0) {
							System.out.println("Tem messagens de: ");
							for(int i = 0;i < users.get(username).getNamesofOwners().size();i++) {
								current = users.get(username).getNamesofOwners().get(i);
								System.out.println("- " + users.get(current).getName() + "(username: " + current+ ")");
							}
							System.out.println("Deseja visualizar alguma dessas mensagens? Se sim, informe o Username do usuario. Se nao, pressione 2");
							seemessage = input.nextLine();
							if(!seemessage.equals("2")) {
								if(users.containsKey(seemessage)) {
									users.get(username).getNamesofOwners().remove(seemessage);
									for(int i  = 0;i < users.get(username).getMyMessages().size();i++) {
										if(users.get(username).getMyMessages().get(i).getOwner().equals(seemessage) || (users.get(username).getMyMessages().get(i).getOwner().equals(username) && users.get(username).getMyMessages().get(i).getFriendReceiver().equals(seemessage))) {
											ownerofmessage = users.get(username).getMyMessages().get(i).getOwner();
											System.out.print(users.get(ownerofmessage).getName() + ": ");
											System.out.println(users.get(username).getMyMessages().get(i).getMessage());
										}
									}
								}
								else System.out.println("Nao foi possivel achar o Usuario.");
							}
						}
						else System.out.println("Nao ha messagens.");
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 8
					else if(option1.equals("8")) {
						boolean acceptcommunity = true;
						String yournewcommunity,newdescription;
						System.out.println("Informe o nome da nova comunidade: ");
						yournewcommunity = input.nextLine();
						System.out.println("Descricao da sua nova comunidade: ");
						newdescription = input.nextLine();
						for(int i = 0;i < systemcommunities.size();i++) {
							if(systemcommunities.get(i).getCommunityName().equals(yournewcommunity)) {
								System.out.println("Comunidade ja existe!");
								acceptcommunity = false;
								break;
							}
						}
						if(acceptcommunity) {
							communityid++;
							Community.AddNewCommunity(systemcommunities,yournewcommunity,username,newdescription,communityid);
							users.get(username).getMyCommunities().put(communityid,yournewcommunity);
							systemcommunities.get(communityid).addMembers(username);
							System.out.println("Comunidade criada com sucesso");
						}
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 9
					else if(option1.equals("9")) {
						String entercommunity;
						if(users.get(username).getMyCommunities().size() > 0) {
							System.out.println("Suas Comunidades: ");
							for(int i = 0;i <= communityid;i++) {
								if(systemcommunities.containsKey(i)) {
									System.out.print(users.get(username).getMyCommunities().get(i));
									if(systemcommunities.get(i).getCommunityAdmin().equals(username)) {
										System.out.println(" - Administrador");
									}
									else System.out.println("");
								}
							}
							System.out.println("Deseja acessar alguma Comunidade? Se sim, informe o nome da Comunidade Se nao, pressione 2");
							entercommunity = input.nextLine();
							if(!entercommunity.equals("2")) {
								int currentid = -1;
								if(users.get(username).getMyCommunities().containsValue(entercommunity)) {
									for(int i = 0; i <= communityid;i++) {
										if(systemcommunities.get(i).getCommunityName().equals(entercommunity)) {
											currentid = systemcommunities.get(i).getCommunityId();
											break;
										}
									}
									Community.EnterCommunity(users,systemcommunities,entercommunity,currentid,username,input);
								}
								else {
									System.out.println("Nao foi possivel achar a Comunidade.");
									System.out.println("Pressione enter para continuar.");
									trash = input.nextLine();
								}
							}
						}
						else {
							System.out.println("Nao faz parte de Comunidade.");
							System.out.println("Pressione enter para continuar.");
							trash = input.nextLine();
						}
					}
					//Option 10
					else if(option1.equals("10")) {
						if(systemcommunities.size() > 0) {
							for(int i = 0; i <= communityid;i++) {
								if(systemcommunities.containsKey(i)) {
									System.out.print("Nome da Comunidade: " + systemcommunities.get(i).getCommunityName());
									System.out.print(" - Id da Comunidade: " + systemcommunities.get(i).getCommunityId());
									System.out.println(" - Administrador: " + systemcommunities.get(i).getCommunityAdmin());
								}
							}
						}
						else System.out.println("Nao existem Comunidades.");
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 11
					else if(option1.equals("11")) {
						System.out.println("Suas Informacoes: ");
						System.out.println("Seu username atual e: " + users.get(username).getUsername());
						System.out.println("Seu nome atual e: " + users.get(username).getName());
						System.out.println("Sua senha atual e: " + users.get(username).getPassword());
						System.out.println("Deseja mudar sua senha?Se sim, pressione 1. Se nao, pressione enter");
						if(users.get(username).getStatusmessage() != null) System.out.println("Seu Status atual e: " + users.get(username).getStatusmessage());
						else System.out.println("Usuario ainda nao tem Status");
						if(users.get(username).getAddress() != null) System.out.println("Seu endereco atual e: " + users.get(username).getAddress());
						else System.out.println("Usuario ainda nao tem Endereco");
						if(users.get(username).getBirthday() != null) System.out.println("Sua data de nascimento atual e: "+ users.get(username).getBirthday());
						else System.out.println("Usuario ainda nao tem Aniversario");
						if(users.get(username).getAge() != 0) System.out.println("Sua idade e: " + users.get(username).getAge());
						else System.out.println("Usuario ainda nao tem Idade.");
						if(users.get(username).getMyCommunities().size() > 0) {
							System.out.println("Suas comunidades: ");
							for(int i = 0;i < systemcommunities.size();i++) {
								System.out.println("Nome da Comunidade: " + users.get(username).getMyCommunities().get(i));
							}
						}
						else System.out.println("Nao faz parte de nenhuma comunidade");
						System.out.println("Numero de amigos: " + users.get(username).getNumberOfFriends());
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					//Option 12
					else if(option1.equals("12")) {
						String removeaccount;
						System.out.println("Sua conta sera apagada, para confirmar, pressione 1. Para cancelar pressione 2");
						removeaccount = input.nextLine();
						if(removeaccount.equals("1")) {
							User.RemoveAccount(users,systemcommunities,username,communityid);
							System.out.println("Conta Removida.Deslogado.");
							loggedin = false;
							newuser = false;
							break;
						}
						System.out.println("Pressione enter para continuar.");
						trash = input.nextLine();
					}
					else if(option1.equals("0")) {
						System.out.println("Deslogado!" + trash + "\n");
						loggedin = false;
						newuser = false;
						break;
					}
				}
			}
			
		}
		input.close();
	}
}
