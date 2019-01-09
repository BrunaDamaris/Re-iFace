package iface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
	private String username;
	private String name;
	private String password;
	private int age;
	private String statusmessage;
	private String address;
	private String birthday;
	private int numberOfFriends;
	private Map<Integer,String> MyCommunities = new HashMap<Integer,String>();
	private ArrayList<String> MyFriends = new ArrayList<String>();
	private ArrayList<Requests> MyRequests = new ArrayList<Requests>();
	private ArrayList<Messages> MyMessages = new ArrayList<Messages>();
	private ArrayList<String> NamesofOwners = new ArrayList<String>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumberOfFriends() {
		return numberOfFriends;
	}
	public void setNumberOfFriends(int numberOfFriends) {
		this.numberOfFriends += numberOfFriends;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Map<Integer,String> getMyCommunities() {
		return MyCommunities;
	}
	public void setMyCommunities(Map<Integer,String> myCommunities) {
		this.MyCommunities = myCommunities;
	}
	public ArrayList<String> getMyFriends() {
		return MyFriends;
	}
	public void setMyFriends(ArrayList<String> myFriends) {
		this.MyFriends = myFriends;
	}
	public ArrayList<Requests> getMyRequests() {
		return MyRequests;
	}
	public void setMyRequests(ArrayList<Requests> myRequests) {
		this.MyRequests = myRequests;
	}
	public ArrayList<Messages> getMyMessages() {
		return MyMessages;
	}
	public void setMyMessages(ArrayList<Messages> myMessages) {
		this.MyMessages = myMessages;
	}
	public ArrayList<String> getNamesofOwners() {
		return NamesofOwners;
	}
	public void setNamesofOwners(ArrayList<String> namesofOwners) {
		this.NamesofOwners = namesofOwners;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getStatusmessage() {
		return statusmessage;
	}
	public void setStatusmessage(String statusmessage) {
		this.statusmessage = statusmessage;
	}
	
	public static void NewUser(Map<String,User> users,String username,String name,String password) {
		User newuser = new User();
		newuser.setName(name);
		newuser.setUsername(username);
		newuser.setPassword(password);
		newuser.setAddress(null);
		newuser.setAge(0);
		newuser.setBirthday(null);
		newuser.setNumberOfFriends(0);
		newuser.setStatusmessage(null);
		users.put(username,newuser);
	}
	
	public void AddRequests(String username,String friendusername) {
		Requests newrequest = new Requests();
		newrequest.setSolicitor(username);
		newrequest.setReceiver(friendusername);
		this.MyRequests.add(newrequest);
	}
	public void AddFriends(String friendusername) {
		this.MyFriends.add(friendusername);
	}
	public void NewMessage(String message,String username,String receiverusername) {
		Messages thismessage = new Messages();
		thismessage.setMessage(message);
		thismessage.setOwner(username);
		thismessage.setFriendReceiver(receiverusername);
		this.MyMessages.add(thismessage);
	}
	public void InMyMessages(String username) {
		this.NamesofOwners.add(username);
	}
	public static void EditProfile(Map<String,User> users,Map<Integer,Community> systemcommunities,String username,Scanner input,int communityid) {
		
		String entry,newi;
		int newage = -1;
		boolean hascommunity = false;
		
		//Change User name
		System.out.println("Seu nome atual e: " + users.get(username).getName());
		System.out.println("Deseja mudar seu nome?Se sim, pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe seu novo Nome: ");
			newi = input.nextLine();
			users.get(username).setName(newi);
		}
		//Change Password
		System.out.println("Sua senha atual e: " + users.get(username).getPassword());
		System.out.println("Deseja mudar sua senha?Se sim, pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe sua nova Senha: ");
			newi = input.nextLine();
			users.get(username).setPassword(newi);
		}
		//Change Status Message
		if(users.get(username).getStatusmessage() != null) System.out.println("Seu Status atual e: " + users.get(username).getStatusmessage());
		else System.out.println("Usuario ainda nao tem Status");
		System.out.println("Deseja modificar/adicionar seu Status?Se sim, pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe seu novo Status: ");
			newi = input.nextLine();
			users.get(username).setStatusmessage(newi);
		}
		//Change Address
		if(users.get(username).getAddress() != null) System.out.println("Seu endereco atual e: " + users.get(username).getAddress());
		else System.out.println("Usuario ainda nao tem Endereco");
		System.out.println("Deseja modificar/adicionar endereco?Se sim, pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe seu novo Endereco: ");
			newi = input.nextLine();
			users.get(username).setAddress(newi);
		}
		//Change Birthday
		if(users.get(username).getBirthday() != null) System.out.println("Sua data de nascimento atual e: "+ users.get(username).getBirthday());
		else System.out.println("Usuario ainda nao tem Aniversario");
		System.out.println("Deseja modificar/adicionar data de nascimento?Se sim, pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe sua data de Nascimento(dia/mes): ");
			newi = input.nextLine();
			users.get(username).setBirthday(newi);
		}
		//Change Age
		if(users.get(username).getAge() != 0) System.out.println("Sua idade e: " + users.get(username).getAge());
		else System.out.println("Usuario ainda nao tem Idade.");
		System.out.println("Deseja modificar/adicionar Idade? Se sim, pressione 1. Se nao, pressione enter.");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe sua Idade: ");
			newage = input.nextInt();
			entry = input.nextLine();
			users.get(username).setAge(newage);
		}
		//See/Exit Community
		if(users.get(username).getMyCommunities().size() > 0) {
			System.out.println("Suas comunidades: ");
			for(int i = 0;i < systemcommunities.size();i++) {
				System.out.println("Nome da Comunidade: " + users.get(username).getMyCommunities().get(i));
			}
			hascommunity = true;
		}
		else System.out.println("Nao faz parte de nenhuma comunidade");
		if(hascommunity){
			int currentid = -1;
			System.out.println("Deseja sair de alguma comunidade? Se sim, pressione 1. Se nao, pressione enter");
			entry = input.nextLine();
			if(entry.equals("1")) {
				String exitcommunity;
				System.out.println("Informe o nome da comunidade na qual deseja sair: ");
				exitcommunity = input.nextLine();
				if(users.get(username).getMyCommunities().containsValue(exitcommunity)) {
					for(int i = 0; i <= communityid;i++) {
						if(systemcommunities.get(i).getCommunityName().equals(exitcommunity)) {
							currentid = systemcommunities.get(i).getCommunityId();
							break;
						}
					}
					if(systemcommunities.containsKey(currentid)) {
						if(systemcommunities.get(currentid).getCommunityAdmin().equals(username)) {
							systemcommunities.remove(currentid);
						}
						else {
							for(int i = 0 ;i <= communityid;i++) {
								if(systemcommunities.get(currentid).getCommunityMembers().get(i).equals(username)) {
									systemcommunities.get(currentid).getCommunityMembers().remove(i);
								}
							}
						}
					}
					else System.out.println("Nao foi possivel achar a Comunidade");
				}
				else System.out.println("Nao foi possivel achar a Comunidade");
			}
		}
	}
	public static void RemoveAccount(Map<String, User> users, Map<Integer, Community> systemcommunities,String username,int communityid) {
		//Exit Communities
		for(int i = 0;i <= communityid;i++) {
			String current = null;
			boolean take = true;
			if(users.get(username).getMyCommunities().containsValue(systemcommunities.get(i).getCommunityName())) {
				if(systemcommunities.get(i).getCommunityAdmin().equals(username)) {
					for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
						try {
							current = systemcommunities.get(i).getCommunityMembers().get(j);
						}
						catch(NullPointerException e) {
							take = false;
						}
						if(take) {
							if(users.containsKey(current)) {
								users.get(current).getMyCommunities().remove(systemcommunities.get(i).getCommunityId());
							}
						}
					}
					systemcommunities.remove(i);
					
				}
				else {
					for(int j = 0 ;j <= communityid;j++) {
						if(systemcommunities.get(i).getCommunityMembers().get(j).equals(username)) {
							systemcommunities.get(i).getCommunityMembers().remove(j);
						}
					}
				}
			}
		}
		//Iterate through Friends
		String currentfriend;
		boolean canremove = false;
		int numberoffriends = users.get(username).getNumberOfFriends();
		for(int i = 0;i < numberoffriends;i++) {
			currentfriend = users.get(username).getMyFriends().get(i);
			if(users.containsKey(currentfriend)) {
				canremove = true;
			}
			if(canremove) {
				for(int j = 0;j < users.get(currentfriend).getMyMessages().size();i++) {
					if(users.get(currentfriend).getMyMessages().get(j).getOwner().equals(username) || users.get(currentfriend).getMyMessages().get(j).getFriendReceiver().equals(username)) {
						users.get(currentfriend).getMyMessages().remove(j);
					}
				}
				for(int j = 0;j < users.get(currentfriend).getMyRequests().size();i++) {
					if(users.get(currentfriend).getMyRequests().get(j).getSolicitor().equals(username)) {
						users.get(currentfriend).getMyRequests().remove(j);
					}
				}
				for(int j = 0;j < users.get(currentfriend).getNamesofOwners().size();i++) {
					if(users.get(currentfriend).getNamesofOwners().get(j).equals(username)) {
						users.get(currentfriend).getNamesofOwners().remove(j);
					}
				}
				users.get(username).getMyFriends().remove(currentfriend);
				users.get(currentfriend).getMyFriends().remove(username);
				users.get(username).setNumberOfFriends(-1);
				users.get(currentfriend).setNumberOfFriends(-1);
				canremove = false;
			}
		}
		//Remove From System
		users.remove(username);
	}
}
