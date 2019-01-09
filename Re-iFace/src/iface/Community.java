package iface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Community {
	private ArrayList<Messages> CommunityMessages = new ArrayList<Messages>();
	private ArrayList<String> CommunityMembers = new ArrayList<String>();
	private int Communityid;
	private String CommunityAdmin;
	private String CommunityName;
	private String CommunityDescription;
	
	public ArrayList<Messages> getCommunityMessages(){ 
		return CommunityMessages;
	}
	//Id
	public void setCommunityId(int id) {
		this.Communityid = id;
	}
	public int getCommunityId() {
		return Communityid;
	}
	//Administrator
	public void setCommunityAdmin(String username) {
		this.CommunityAdmin = username;
	}
	public String getCommunityAdmin() {
		return CommunityAdmin;
	}
	//Members
	public void addMembers(String username) {
		if(!(CommunityMembers.contains(username))) {
			this.CommunityMembers.add(username);
			System.out.println("Usuario adicionado!");
		}
		else System.out.println("Esse usuario ja e membro!");
	}
	public ArrayList<String> getCommunityMembers(){
		return CommunityMembers;
	}
	//Name
	public void setCommunityName(String Cname) {
		this.CommunityName = Cname;
	}
	public String getCommunityName() {
		return CommunityName;
	}
	//Description
	public void setCommunityDescription(String description) {
		this.CommunityDescription = description;
	}
	public String getCommunityDescription() {
		return CommunityDescription;
	}
	
	public static void AddNewCommunity(Map<Integer,Community> communities,String nameofcommunity,String admin,String description,int communityId) {
		Community newcommunity = new Community();
		newcommunity.setCommunityId(communityId);
		newcommunity.setCommunityName(nameofcommunity);
		newcommunity.setCommunityAdmin(admin);
		newcommunity.setCommunityDescription(description);
		communities.put(communityId,newcommunity);
	}
	public void AddCommunityMessages(String message,String username) {
		Messages newm = new Messages();
		newm.setMessage(message);
		newm.setOwner(username);
		newm.setFriendReceiver("Todos");
		this.CommunityMessages.add(newm);
	}
	
	//CommunityOptions
	public static void EnterCommunity(Map<String,User> users,Map<Integer,Community> communities,String currentcommunity,int communityid,String username,Scanner input) {
		String option = null;
		if(communities.get(communityid).getCommunityAdmin().equals(username)) {
			System.out.println("Voce e o administrador!");
			while(true) {
				System.out.println("\nVer Membros(1)\n\nAdicionar Membro(2)\n\nRetirar Membro(3)\n\nEnviar Mensagem para Comunidade(4)\n\nVisualizar Mensagens da Comunidade(5)\n\nVoltar ao menu(0)");
				option = input.nextLine();
				if(option.equals("0")) {
					break;
				}
				else if(option.equals("1")) {
					String current,name = null;
					boolean accept = true;
					System.out.println("Membros:\n");
					for(int i = 0;i < communities.get(communityid).getCommunityMembers().size();i++) {
						current = communities.get(communityid).getCommunityMembers().get(i);
						try {
							name = users.get(current).getName();
						}
						catch(NullPointerException e) {
							accept = false;
						}
						if(accept) System.out.println(name + "(" + current +")");
					}
				}
				else if(option.equals("2")) {
					String newmember,current;
					System.out.println("Informe o Username do Usuario que deseja adicionar: ");
					newmember = input.nextLine();
					if(users.containsKey(newmember)) {
						communities.get(communityid).addMembers(newmember);
						current = users.get(newmember).getName();
						users.get(newmember).getMyCommunities().put(communityid,communities.get(communityid).getCommunityName());
						communities.get(communityid).AddCommunityMessages(current + " foi adicionado", "Admin Status Message");
					}
					else System.out.println("Usuario nao existe.");
				}
				else if(option.equals("3")) {
					String removemember,current;
					boolean removevalidy = false;
					System.out.println("Informe o Username do Usuario que deseja remover: ");
					removemember = input.nextLine();
					if(users.containsKey(removemember)) {
						for(int i = 0;i < communities.get(communityid).getCommunityMembers().size();i++) {
							if(communities.get(communityid).getCommunityMembers().get(i).equals(removemember)) {
								communities.get(communityid).getCommunityMembers().remove(removemember);
								current = users.get(removemember).getName();
								users.get(removemember).getMyCommunities().remove(communityid);
								System.out.println("Usuario removido.");
								communities.get(communityid).AddCommunityMessages(current + " foi removido", "Admin Status Message");
								removevalidy = true;
							}
						}
						if(!removevalidy) System.out.println("Nao foi possivel remover.");
					}
					else System.out.println("Usuario nao existe.");
				}
				else if(option.equals("4")) {
					String communitymessage;
					System.out.println("Sua Mensagem: ");
					communitymessage = input.nextLine();
					communities.get(communityid).AddCommunityMessages(communitymessage, username);
				}
				else if(option.equals("5")) {
					String current,name;
					if(communities.get(communityid).getCommunityMessages().size() != 0) {
						System.out.println("Mensagens: ");
						//Try
						for(int i  = 0;i < communities.get(communityid).getCommunityMessages().size();i++) {
							current = communities.get(communityid).getCommunityMessages().get(i).getOwner();
							try {
								name = users.get(current).getName();
							}
							catch(NullPointerException e) {
								name = " ";
							}
							System.out.print(name + "(" + current + ")"+ ": ");
							System.out.println(communities.get(communityid).getCommunityMessages().get(i).getMessage());
						}
					}
					else System.out.println("Nao ha mensagens.");
					System.out.println("\nPressione enter para continuar.");
					current = input.nextLine();
				}
			}
		}
		else {
			while(true) {
				System.out.println("\nVer Membros(1)\n\nEnviar Mensagem para Comunidade(2)\n\nVisualizar Mensagens da Comunidade(3)\n\nVer administrador(4)\n\nVoltar ao menu(0)");
				option = input.nextLine();
				if(option.equals("0")) {
					break;
				}
				else if(option.equals("1")) {
					String current,name = null;
					boolean accept = true;
					System.out.println("Membros:\n");
					for(int i = 0;i < communities.get(communityid).getCommunityMembers().size();i++) {
						current = communities.get(communityid).getCommunityMembers().get(i);
						try {
							name = users.get(current).getName();
						}
						catch(NullPointerException e) {
							accept = false;
						}
						if(accept) System.out.println(name + "(" + current +")");
					}
					System.out.println("Pressione enter para continuar.");
					current = input.nextLine();
				}
				else if(option.equals("2")) {
					String communitymessage;
					System.out.println("Sua Mensagem: ");
					communitymessage = input.nextLine();
					communities.get(communityid).AddCommunityMessages(communitymessage, username);
				}
				else if(option.equals("3")) {
					String current,name;
					if(communities.get(communityid).getCommunityMessages().size() != 0) {
						System.out.println("Mensagens:\n" );
						//Try
						for(int i  = 0;i < communities.get(communityid).getCommunityMessages().size();i++) {
							current = communities.get(communityid).getCommunityMessages().get(i).getOwner();
							try {
								name = users.get(current).getName();
							}
							catch(NullPointerException e) {
								name = " ";
							}
							System.out.print(name + "(" + current + ")"+ ": ");
							System.out.println(communities.get(communityid).getCommunityMessages().get(i).getMessage());
						}
					}
					else System.out.println("Nao ha mensagens.");
					System.out.println("\nPressione enter para continuar.");
					current = input.nextLine();
				}
				else if(option.equals("4")) {
					System.out.println("Administrador: " + communities.get(communityid).getCommunityAdmin());
				}
			}
		}
	}
}
