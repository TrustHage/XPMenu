package me.stadio.xp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	private void GivePlayerExp15(Player player)
	{
		player.giveExpLevels(15);
	}
	
	private void openGUI(Player player)
	{
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		
		Inventory mainmenu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Xp Shop");
		
		ItemStack buy = new ItemStack(Material.BOOK);
		ItemMeta buyMeta = buy.getItemMeta();
		lore.add(String.format("%sYou can buy here %sDiamonds %sor %sGold", ChatColor.RED, ChatColor.AQUA, ChatColor.RED, ChatColor.GOLD));
		lore.add(String.format("          %swith your XP          ", ChatColor.RED));
		buyMeta.setLore(lore);
		
		ItemStack sell = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta sellMeta = sell.getItemMeta();
		lore2.add(String.format("%sYou can sell here your %sDiamonds %sand %sGold", ChatColor.RED, ChatColor.AQUA, ChatColor.RED, ChatColor.GOLD));
		lore2.add(String.format("                 %sfor xp                 ", ChatColor.RED));
		sellMeta.setLore(lore2);
		
		ItemStack close = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta closeMeta = close.getItemMeta();
		
		buyMeta.setDisplayName(ChatColor.GREEN + "Buy");
		buy.setItemMeta(buyMeta);
		
		
		sellMeta.setDisplayName(ChatColor.BLUE + "Sell");
		sell.setItemMeta(sellMeta);
		
		closeMeta.setDisplayName(ChatColor.RED + "Quit");
		close.setItemMeta(closeMeta);
		
		mainmenu.setItem(3, buy);
		mainmenu.setItem(5, sell);
		mainmenu.setItem(8, close);
		
		player.openInventory(mainmenu);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Xp Shop"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case BOOK:
			openBuyMenu(player.getPlayer());
			break;
		case EXP_BOTTLE:
			openSellMenu(player.getPlayer());
			break;
		case LAVA_BUCKET:
			player.closeInventory();
			player.sendMessage(ChatColor.RED + "You closed the Xp Shop");
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("xpshop"))
		{
			openGUI(player.getPlayer());
		}
		return false;
	}
	
	private void openBuyMenu(Player player)
	{
		Inventory buymenu = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Buy Menu");
		
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta diamondMeta = diamond.getItemMeta();
		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		ItemStack back = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta backMeta = back.getItemMeta();
		
		diamondMeta.setDisplayName(ChatColor.AQUA + "Diamond");
		diamond.setItemMeta(diamondMeta);
		
		goldMeta.setDisplayName(ChatColor.GOLD + "Gold");
		gold.setItemMeta(goldMeta);
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		buymenu.setItem(3, diamond);
		buymenu.setItem(5, gold);
		buymenu.setItem(8, back);
		
		player.openInventory(buymenu);
	}
	
	@EventHandler
	public void onBuyMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Buy Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case DIAMOND:
			openDiamondBuyMenu(player.getPlayer());
			break;
		case GOLD_INGOT:
			openGoldBuyMenu(player.getPlayer());
			break;
		case LAVA_BUCKET:
			openGUI(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	private void openSellMenu(Player player)
	{
		Inventory sellmenu = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Sell Menu");
		
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta diamondMeta = diamond.getItemMeta();
		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		ItemStack back = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta backMeta = back.getItemMeta();
		
		diamondMeta.setDisplayName(ChatColor.AQUA + "Diamond");
		diamond.setItemMeta(diamondMeta);
		
		goldMeta.setDisplayName(ChatColor.GOLD + "Gold");
		gold.setItemMeta(goldMeta);
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		sellmenu.setItem(3, diamond);
		sellmenu.setItem(5, gold);
		sellmenu.setItem(8, back);
		
		player.openInventory(sellmenu);
	}
	
	@EventHandler
	public void onSellMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Sell Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case DIAMOND:
			openDiamondSellMenu(player.getPlayer());
			break;
		case GOLD_INGOT:
			openGoldSellMenu(player.getPlayer());
			break;
		case LAVA_BUCKET:
			openGUI(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	private void openDiamondBuyMenu(Player player)
	{
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		ArrayList<String> lore3 = new ArrayList<String>();
		ArrayList<String> lore4 = new ArrayList<String>();
		ArrayList<String> lore5 = new ArrayList<String>();
		ArrayList<String> lore6 = new ArrayList<String>();
		ArrayList<String> lore7 = new ArrayList<String>();
		ArrayList<String> lore8 = new ArrayList<String>();
		ArrayList<String> lore9 = new ArrayList<String>();
		ArrayList<String> lore10 = new ArrayList<String>();
		ArrayList<String> lore11 = new ArrayList<String>();
		ArrayList<String> lore12 = new ArrayList<String>();
		ArrayList<String> lore13 = new ArrayList<String>();
		ArrayList<String> lore14 = new ArrayList<String>();
		ArrayList<String> lore15 = new ArrayList<String>();
		
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Diamond Buy Menu");
		
		ItemStack diamond4 = new ItemStack(Material.DIAMOND, 4);
		ItemMeta diamond4Meta = diamond4.getItemMeta();
		lore.add(ChatColor.RED + "Cost: XP");
		
		ItemStack diamond8 = new ItemStack(Material.DIAMOND, 8);
		ItemMeta diamond8Meta = diamond8.getItemMeta();
		lore1.add(ChatColor.RED + "Cost: XP");
		
		ItemStack diamond12 = new ItemStack(Material.DIAMOND, 12);
		ItemMeta diamond12Meta = diamond12.getItemMeta();
		lore2.add(ChatColor.RED + "Cost: XP");
		
		ItemStack diamond16 = new ItemStack(Material.DIAMOND, 16);
		ItemMeta diamond16Meta = diamond16.getItemMeta();
		lore3.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond20 = new ItemStack(Material.DIAMOND, 20);
		ItemMeta diamond20Meta = diamond20.getItemMeta();
		lore4.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond24 = new ItemStack(Material.DIAMOND, 24);
		ItemMeta diamond24Meta = diamond24.getItemMeta();
		lore5.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond28 = new ItemStack(Material.DIAMOND, 28);
		ItemMeta diamond28Meta = diamond28.getItemMeta();
		lore6.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond32 = new ItemStack(Material.DIAMOND, 32);
		ItemMeta diamond32Meta = diamond32.getItemMeta();
		lore7.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond36 = new ItemStack(Material.DIAMOND, 36);
		ItemMeta diamond36Meta = diamond36.getItemMeta();
		lore8.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond40 = new ItemStack(Material.DIAMOND, 40);
		ItemMeta diamond40Meta = diamond40.getItemMeta();
		lore9.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond44 = new ItemStack(Material.DIAMOND, 44);
		ItemMeta diamond44Meta = diamond44.getItemMeta();
		lore10.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond48 = new ItemStack(Material.DIAMOND, 48);
		ItemMeta diamond48Meta = diamond48.getItemMeta();
		lore11.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond52 = new ItemStack(Material.DIAMOND, 52);
		ItemMeta diamond52Meta = diamond52.getItemMeta();
		lore12.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond56 = new ItemStack(Material.DIAMOND, 56);
		ItemMeta diamond56Meta = diamond56.getItemMeta();
		lore13.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond60 = new ItemStack(Material.DIAMOND, 60);
		ItemMeta diamond60Meta = diamond60.getItemMeta();
		lore14.add(ChatColor.RED + "Cost: XP");

		ItemStack diamond64 = new ItemStack(Material.DIAMOND, 64);
		ItemMeta diamond64Meta = diamond64.getItemMeta();
		lore15.add(ChatColor.RED + "Cost: XP");
		
		diamond4Meta.setLore(lore);
		diamond8Meta.setLore(lore1);
		diamond12Meta.setLore(lore2);
		diamond16Meta.setLore(lore3);
		diamond20Meta.setLore(lore4);
		diamond24Meta.setLore(lore5);
		diamond28Meta.setLore(lore6);
		diamond32Meta.setLore(lore7);
		diamond36Meta.setLore(lore8);
		diamond40Meta.setLore(lore9);
		diamond44Meta.setLore(lore10);
		diamond48Meta.setLore(lore11);
		diamond52Meta.setLore(lore12);
		diamond56Meta.setLore(lore13);
		diamond60Meta.setLore(lore14);
		diamond64Meta.setLore(lore15);
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		diamond4Meta.setDisplayName(String.format("%sDiamond %sx4", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond4.setItemMeta(diamond4Meta);
		
		diamond8Meta.setDisplayName(String.format("%sDiamond %sx8", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond8.setItemMeta(diamond8Meta);
		
		diamond12Meta.setDisplayName(String.format("%sDiamond %sx12", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond12.setItemMeta(diamond12Meta);

		diamond16Meta.setDisplayName(String.format("%sDiamond %sx16", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond16.setItemMeta(diamond16Meta);

		diamond20Meta.setDisplayName(String.format("%sDiamond %sx20", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond20.setItemMeta(diamond20Meta);

		diamond24Meta.setDisplayName(String.format("%sDiamond %sx24", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond24.setItemMeta(diamond24Meta);

		diamond28Meta.setDisplayName(String.format("%sDiamond %sx28", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond28.setItemMeta(diamond28Meta);

		diamond32Meta.setDisplayName(String.format("%sDiamond %sx32", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond32.setItemMeta(diamond32Meta);

		diamond36Meta.setDisplayName(String.format("%sDiamond %sx36", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond36.setItemMeta(diamond36Meta);

		diamond40Meta.setDisplayName(String.format("%sDiamond %sx40", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond40.setItemMeta(diamond40Meta);

		diamond44Meta.setDisplayName(String.format("%sDiamond %sx44", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond44.setItemMeta(diamond44Meta);

		diamond48Meta.setDisplayName(String.format("%sDiamond %sx48", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond48.setItemMeta(diamond48Meta);

		diamond52Meta.setDisplayName(String.format("%sDiamond %sx52", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond52.setItemMeta(diamond52Meta);

		diamond56Meta.setDisplayName(String.format("%sDiamond %sx56", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond56.setItemMeta(diamond56Meta);

		diamond60Meta.setDisplayName(String.format("%sDiamond %sx60", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond60.setItemMeta(diamond60Meta);

		diamond64Meta.setDisplayName(String.format("%sDiamond %sx64", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond64.setItemMeta(diamond64Meta);		
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		buymenu.setItem(0, diamond4);
		buymenu.setItem(1, diamond8);
		buymenu.setItem(2, diamond12);
		buymenu.setItem(3, diamond16);
		buymenu.setItem(4, diamond20);
		buymenu.setItem(5, diamond24);
		buymenu.setItem(6, diamond28);
		buymenu.setItem(7, diamond32);
		buymenu.setItem(8, diamond36);
		buymenu.setItem(10, diamond40);
		buymenu.setItem(11, diamond44);
		buymenu.setItem(12, diamond48);
		buymenu.setItem(13, diamond52);
		buymenu.setItem(14, diamond56);
		buymenu.setItem(15, diamond60);
		buymenu.setItem(16, diamond64);
		buymenu.setItem(22, back);
		
		player.openInventory(buymenu);
	}
	
	@EventHandler
	public void onDiamondBuyMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Diamond Buy Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getAmount())
		{
		case 4:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
			break;
		case 8:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 12:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 1:
			openBuyMenu(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	private void openGoldBuyMenu(Player player)
	{
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		ArrayList<String> lore3 = new ArrayList<String>();
		ArrayList<String> lore4 = new ArrayList<String>();
		ArrayList<String> lore5 = new ArrayList<String>();
		ArrayList<String> lore6 = new ArrayList<String>();
		ArrayList<String> lore7 = new ArrayList<String>();
		ArrayList<String> lore8 = new ArrayList<String>();
		ArrayList<String> lore9 = new ArrayList<String>();
		ArrayList<String> lore10 = new ArrayList<String>();
		ArrayList<String> lore11 = new ArrayList<String>();
		ArrayList<String> lore12 = new ArrayList<String>();
		ArrayList<String> lore13 = new ArrayList<String>();
		ArrayList<String> lore14 = new ArrayList<String>();
		ArrayList<String> lore15 = new ArrayList<String>();
		
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Gold Buy Menu");
		
		ItemStack gold4 = new ItemStack(Material.GOLD_INGOT, 4);
		ItemMeta gold4Meta = gold4.getItemMeta();
		lore.add(ChatColor.RED + "Cost: XP");
		
		ItemStack gold8 = new ItemStack(Material.GOLD_INGOT, 8);
		ItemMeta gold8Meta = gold8.getItemMeta();
		lore1.add(ChatColor.RED + "Cost: XP");
		
		ItemStack gold12 = new ItemStack(Material.GOLD_INGOT, 12);
		ItemMeta gold12Meta = gold12.getItemMeta();
		lore2.add(ChatColor.RED + "Cost: XP");
		
		ItemStack gold16 = new ItemStack(Material.GOLD_INGOT, 16);
		ItemMeta gold16Meta = gold16.getItemMeta();
		lore3.add(ChatColor.RED + "Cost: XP");

		ItemStack gold20 = new ItemStack(Material.GOLD_INGOT, 20);
		ItemMeta gold20Meta = gold20.getItemMeta();
		lore4.add(ChatColor.RED + "Cost: XP");

		ItemStack gold24 = new ItemStack(Material.GOLD_INGOT, 24);
		ItemMeta gold24Meta = gold24.getItemMeta();
		lore5.add(ChatColor.RED + "Cost: XP");

		ItemStack gold28 = new ItemStack(Material.GOLD_INGOT, 28);
		ItemMeta gold28Meta = gold28.getItemMeta();
		lore6.add(ChatColor.RED + "Cost: XP");

		ItemStack gold32 = new ItemStack(Material.GOLD_INGOT, 32);
		ItemMeta gold32Meta = gold32.getItemMeta();
		lore7.add(ChatColor.RED + "Cost: XP");

		ItemStack gold36 = new ItemStack(Material.GOLD_INGOT, 36);
		ItemMeta gold36Meta = gold36.getItemMeta();
		lore8.add(ChatColor.RED + "Cost: XP");

		ItemStack gold40 = new ItemStack(Material.GOLD_INGOT, 40);
		ItemMeta gold40Meta = gold40.getItemMeta();
		lore9.add(ChatColor.RED + "Cost: XP");

		ItemStack gold44 = new ItemStack(Material.GOLD_INGOT, 44);
		ItemMeta gold44Meta = gold44.getItemMeta();
		lore10.add(ChatColor.RED + "Cost: XP");

		ItemStack gold48 = new ItemStack(Material.GOLD_INGOT, 48);
		ItemMeta gold48Meta = gold48.getItemMeta();
		lore11.add(ChatColor.RED + "Cost: XP");

		ItemStack gold52 = new ItemStack(Material.GOLD_INGOT, 52);
		ItemMeta gold52Meta = gold52.getItemMeta();
		lore12.add(ChatColor.RED + "Cost: XP");

		ItemStack gold56 = new ItemStack(Material.GOLD_INGOT, 56);
		ItemMeta gold56Meta = gold56.getItemMeta();
		lore13.add(ChatColor.RED + "Cost: XP");

		ItemStack gold60 = new ItemStack(Material.GOLD_INGOT, 60);
		ItemMeta gold60Meta = gold60.getItemMeta();
		lore14.add(ChatColor.RED + "Cost: XP");

		ItemStack gold64 = new ItemStack(Material.GOLD_INGOT, 64);
		ItemMeta gold64Meta = gold64.getItemMeta();
		lore15.add(ChatColor.RED + "Cost: XP");
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		gold4Meta.setLore(lore);
		gold8Meta.setLore(lore1);
		gold12Meta.setLore(lore2);
		gold16Meta.setLore(lore3);
		gold20Meta.setLore(lore4);
		gold24Meta.setLore(lore5);
		gold28Meta.setLore(lore6);
		gold32Meta.setLore(lore7);
		gold36Meta.setLore(lore8);
		gold40Meta.setLore(lore9);
		gold44Meta.setLore(lore10);
		gold48Meta.setLore(lore11);
		gold52Meta.setLore(lore12);
		gold56Meta.setLore(lore13);
		gold60Meta.setLore(lore14);
		gold64Meta.setLore(lore15);
		
		gold4Meta.setDisplayName(String.format("%sGold Ingot %sx4", ChatColor.GOLD, ChatColor.DARK_RED));
		gold4.setItemMeta(gold4Meta);
		
		gold8Meta.setDisplayName(String.format("%sGold Ingot %sx8", ChatColor.GOLD, ChatColor.DARK_RED));
		gold8.setItemMeta(gold8Meta);
		
		gold12Meta.setDisplayName(String.format("%sGold Ingot %sx12", ChatColor.GOLD, ChatColor.DARK_RED));
		gold12.setItemMeta(gold12Meta);

		gold16Meta.setDisplayName(String.format("%sGold Ingot %sx16", ChatColor.GOLD, ChatColor.DARK_RED));
		gold16.setItemMeta(gold16Meta);

		gold20Meta.setDisplayName(String.format("%sGold Ingot %sx20", ChatColor.GOLD, ChatColor.DARK_RED));
		gold20.setItemMeta(gold20Meta);

		gold24Meta.setDisplayName(String.format("%sGold Ingot %sx24", ChatColor.GOLD, ChatColor.DARK_RED));
		gold24.setItemMeta(gold24Meta);

		gold28Meta.setDisplayName(String.format("%sGold Ingot %sx28", ChatColor.GOLD, ChatColor.DARK_RED));
		gold28.setItemMeta(gold28Meta);

		gold32Meta.setDisplayName(String.format("%sGold Ingot %sx32", ChatColor.GOLD, ChatColor.DARK_RED));
		gold32.setItemMeta(gold32Meta);

		gold36Meta.setDisplayName(String.format("%sGold Ingot %sx36", ChatColor.GOLD, ChatColor.DARK_RED));
		gold36.setItemMeta(gold36Meta);

		gold40Meta.setDisplayName(String.format("%sGold Ingot %sx40", ChatColor.GOLD, ChatColor.DARK_RED));
		gold40.setItemMeta(gold40Meta);

		gold44Meta.setDisplayName(String.format("%sGold Ingot %sx44", ChatColor.GOLD, ChatColor.DARK_RED));
		gold44.setItemMeta(gold44Meta);

		gold48Meta.setDisplayName(String.format("%sGold Ingot %sx48", ChatColor.GOLD, ChatColor.DARK_RED));
		gold48.setItemMeta(gold48Meta);

		gold52Meta.setDisplayName(String.format("%sGold Ingot %sx52", ChatColor.GOLD, ChatColor.DARK_RED));
		gold52.setItemMeta(gold52Meta);

		gold56Meta.setDisplayName(String.format("%sGold Ingot %sx56", ChatColor.GOLD, ChatColor.DARK_RED));
		gold56.setItemMeta(gold56Meta);

		gold60Meta.setDisplayName(String.format("%sGold Ingot %sx60", ChatColor.GOLD, ChatColor.DARK_RED));
		gold60.setItemMeta(gold60Meta);

		gold64Meta.setDisplayName(String.format("%sGold Ingot %sx64", ChatColor.GOLD, ChatColor.DARK_RED));
		gold64.setItemMeta(gold64Meta);		
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		buymenu.setItem(0, gold4);
		buymenu.setItem(1, gold8);
		buymenu.setItem(2, gold12);
		buymenu.setItem(3, gold16);
		buymenu.setItem(4, gold20);
		buymenu.setItem(5, gold24);
		buymenu.setItem(6, gold28);
		buymenu.setItem(7, gold32);
		buymenu.setItem(8, gold36);
		buymenu.setItem(10, gold40);
		buymenu.setItem(11, gold44);
		buymenu.setItem(12, gold48);
		buymenu.setItem(13, gold52);
		buymenu.setItem(14, gold56);
		buymenu.setItem(15, gold60);
		buymenu.setItem(16, gold64);
		buymenu.setItem(22, back);
		
		player.openInventory(buymenu);
	}
	
	@EventHandler
	public void onGoldBuyMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Gold Buy Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getAmount())
		{
		case 4:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
			break;
		case 8:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 12:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 1:
			openBuyMenu(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
}
	}
	
	private void openDiamondSellMenu(Player player)
	{
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		ArrayList<String> lore3 = new ArrayList<String>();
		ArrayList<String> lore4 = new ArrayList<String>();
		ArrayList<String> lore5 = new ArrayList<String>();
		ArrayList<String> lore6 = new ArrayList<String>();
		ArrayList<String> lore7 = new ArrayList<String>();
		ArrayList<String> lore8 = new ArrayList<String>();
		ArrayList<String> lore9 = new ArrayList<String>();
		ArrayList<String> lore10 = new ArrayList<String>();
		ArrayList<String> lore11 = new ArrayList<String>();
		ArrayList<String> lore12 = new ArrayList<String>();
		ArrayList<String> lore13 = new ArrayList<String>();
		ArrayList<String> lore14 = new ArrayList<String>();
		ArrayList<String> lore15 = new ArrayList<String>();
		
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Diamond Sell Menu");
		
		ItemStack diamond4 = new ItemStack(Material.DIAMOND, 4);
		ItemMeta diamond4Meta = diamond4.getItemMeta();
		lore.add(ChatColor.RED + "Get: XP");
		
		ItemStack diamond8 = new ItemStack(Material.DIAMOND, 8);
		ItemMeta diamond8Meta = diamond8.getItemMeta();
		lore1.add(ChatColor.RED + "Get: XP");
		
		ItemStack diamond12 = new ItemStack(Material.DIAMOND, 12);
		ItemMeta diamond12Meta = diamond12.getItemMeta();
		lore2.add(ChatColor.RED + "Get: XP");
		
		ItemStack diamond16 = new ItemStack(Material.DIAMOND, 16);
		ItemMeta diamond16Meta = diamond16.getItemMeta();
		lore3.add(ChatColor.RED + "Get: XP");

		ItemStack diamond20 = new ItemStack(Material.DIAMOND, 20);
		ItemMeta diamond20Meta = diamond20.getItemMeta();
		lore4.add(ChatColor.RED + "Get: XP");

		ItemStack diamond24 = new ItemStack(Material.DIAMOND, 24);
		ItemMeta diamond24Meta = diamond24.getItemMeta();
		lore5.add(ChatColor.RED + "Get: XP");

		ItemStack diamond28 = new ItemStack(Material.DIAMOND, 28);
		ItemMeta diamond28Meta = diamond28.getItemMeta();
		lore6.add(ChatColor.RED + "Get: XP");

		ItemStack diamond32 = new ItemStack(Material.DIAMOND, 32);
		ItemMeta diamond32Meta = diamond32.getItemMeta();
		lore7.add(ChatColor.RED + "Get: XP");

		ItemStack diamond36 = new ItemStack(Material.DIAMOND, 36);
		ItemMeta diamond36Meta = diamond36.getItemMeta();
		lore8.add(ChatColor.RED + "Get: XP");

		ItemStack diamond40 = new ItemStack(Material.DIAMOND, 40);
		ItemMeta diamond40Meta = diamond40.getItemMeta();
		lore9.add(ChatColor.RED + "Get: XP");

		ItemStack diamond44 = new ItemStack(Material.DIAMOND, 44);
		ItemMeta diamond44Meta = diamond44.getItemMeta();
		lore10.add(ChatColor.RED + "Get: XP");

		ItemStack diamond48 = new ItemStack(Material.DIAMOND, 48);
		ItemMeta diamond48Meta = diamond48.getItemMeta();
		lore11.add(ChatColor.RED + "Get: XP");

		ItemStack diamond52 = new ItemStack(Material.DIAMOND, 52);
		ItemMeta diamond52Meta = diamond52.getItemMeta();
		lore12.add(ChatColor.RED + "Get: XP");

		ItemStack diamond56 = new ItemStack(Material.DIAMOND, 56);
		ItemMeta diamond56Meta = diamond56.getItemMeta();
		lore13.add(ChatColor.RED + "Get: XP");

		ItemStack diamond60 = new ItemStack(Material.DIAMOND, 60);
		ItemMeta diamond60Meta = diamond60.getItemMeta();
		lore14.add(ChatColor.RED + "Get: XP");

		ItemStack diamond64 = new ItemStack(Material.DIAMOND, 64);
		ItemMeta diamond64Meta = diamond64.getItemMeta();
		lore15.add(ChatColor.RED + "Get: XP");
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		diamond4Meta.setLore(lore);
		diamond8Meta.setLore(lore1);
		diamond12Meta.setLore(lore2);
		diamond16Meta.setLore(lore3);
		diamond20Meta.setLore(lore4);
		diamond24Meta.setLore(lore5);
		diamond28Meta.setLore(lore6);
		diamond32Meta.setLore(lore7);
		diamond36Meta.setLore(lore8);
		diamond40Meta.setLore(lore9);
		diamond44Meta.setLore(lore10);
		diamond48Meta.setLore(lore11);
		diamond52Meta.setLore(lore12);
		diamond56Meta.setLore(lore13);
		diamond60Meta.setLore(lore14);
		diamond64Meta.setLore(lore15);
		
		diamond4Meta.setDisplayName(String.format("%sDiamond %sx4", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond4.setItemMeta(diamond4Meta);
		
		diamond8Meta.setDisplayName(String.format("%sDiamond %sx8", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond8.setItemMeta(diamond8Meta);
		
		diamond12Meta.setDisplayName(String.format("%sDiamond %sx12", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond12.setItemMeta(diamond12Meta);

		diamond16Meta.setDisplayName(String.format("%sDiamond %sx16", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond16.setItemMeta(diamond16Meta);

		diamond20Meta.setDisplayName(String.format("%sDiamond %sx20", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond20.setItemMeta(diamond20Meta);

		diamond24Meta.setDisplayName(String.format("%sDiamond %sx24", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond24.setItemMeta(diamond24Meta);

		diamond28Meta.setDisplayName(String.format("%sDiamond %sx28", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond28.setItemMeta(diamond28Meta);

		diamond32Meta.setDisplayName(String.format("%sDiamond %sx32", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond32.setItemMeta(diamond32Meta);

		diamond36Meta.setDisplayName(String.format("%sDiamond %sx36", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond36.setItemMeta(diamond36Meta);

		diamond40Meta.setDisplayName(String.format("%sDiamond %sx40", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond40.setItemMeta(diamond40Meta);

		diamond44Meta.setDisplayName(String.format("%sDiamond %sx44", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond44.setItemMeta(diamond44Meta);

		diamond48Meta.setDisplayName(String.format("%sDiamond %sx48", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond48.setItemMeta(diamond48Meta);

		diamond52Meta.setDisplayName(String.format("%sDiamond %sx52", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond52.setItemMeta(diamond52Meta);

		diamond56Meta.setDisplayName(String.format("%sDiamond %sx56", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond56.setItemMeta(diamond56Meta);

		diamond60Meta.setDisplayName(String.format("%sDiamond %sx60", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond60.setItemMeta(diamond60Meta);

		diamond64Meta.setDisplayName(String.format("%sDiamond %sx64", ChatColor.AQUA, ChatColor.DARK_RED));
		diamond64.setItemMeta(diamond64Meta);		
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		buymenu.setItem(0, diamond4);
		buymenu.setItem(1, diamond8);
		buymenu.setItem(2, diamond12);
		buymenu.setItem(3, diamond16);
		buymenu.setItem(4, diamond20);
		buymenu.setItem(5, diamond24);
		buymenu.setItem(6, diamond28);
		buymenu.setItem(7, diamond32);
		buymenu.setItem(8, diamond36);
		buymenu.setItem(10, diamond40);
		buymenu.setItem(11, diamond44);
		buymenu.setItem(12, diamond48);
		buymenu.setItem(13, diamond52);
		buymenu.setItem(14, diamond56);
		buymenu.setItem(15, diamond60);
		buymenu.setItem(16, diamond64);
		buymenu.setItem(22, back);
		
		player.openInventory(buymenu);
	}
	
	@EventHandler
	public void onDiamondSellMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Diamond Sell Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getAmount())
		{
		case 4:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
			break;
		case 8:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 12:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 1:
			openSellMenu(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
		}
	}
	
	private void openGoldSellMenu(Player player)
	{
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<String> lore1 = new ArrayList<String>();
		ArrayList<String> lore2 = new ArrayList<String>();
		ArrayList<String> lore3 = new ArrayList<String>();
		ArrayList<String> lore4 = new ArrayList<String>();
		ArrayList<String> lore5 = new ArrayList<String>();
		ArrayList<String> lore6 = new ArrayList<String>();
		ArrayList<String> lore7 = new ArrayList<String>();
		ArrayList<String> lore8 = new ArrayList<String>();
		ArrayList<String> lore9 = new ArrayList<String>();
		ArrayList<String> lore10 = new ArrayList<String>();
		ArrayList<String> lore11 = new ArrayList<String>();
		ArrayList<String> lore12 = new ArrayList<String>();
		ArrayList<String> lore13 = new ArrayList<String>();
		ArrayList<String> lore14 = new ArrayList<String>();
		ArrayList<String> lore15 = new ArrayList<String>();
		
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Gold Sell Menu");
		
		ItemStack gold4 = new ItemStack(Material.GOLD_INGOT, 4);
		ItemMeta gold4Meta = gold4.getItemMeta();
		lore.add(ChatColor.RED + "Get: XP");
		
		ItemStack gold8 = new ItemStack(Material.GOLD_INGOT, 8);
		ItemMeta gold8Meta = gold8.getItemMeta();
		lore1.add(ChatColor.RED + "Get: XP");
		
		ItemStack gold12 = new ItemStack(Material.GOLD_INGOT, 12);
		ItemMeta gold12Meta = gold12.getItemMeta();
		lore2.add(ChatColor.RED + "Get: XP");
		
		ItemStack gold16 = new ItemStack(Material.GOLD_INGOT, 16);
		ItemMeta gold16Meta = gold16.getItemMeta();
		lore3.add(ChatColor.RED + "Get: XP");

		ItemStack gold20 = new ItemStack(Material.GOLD_INGOT, 20);
		ItemMeta gold20Meta = gold20.getItemMeta();
		lore4.add(ChatColor.RED + "Get: XP");

		ItemStack gold24 = new ItemStack(Material.GOLD_INGOT, 24);
		ItemMeta gold24Meta = gold24.getItemMeta();
		lore5.add(ChatColor.RED + "Get: XP");

		ItemStack gold28 = new ItemStack(Material.GOLD_INGOT, 28);
		ItemMeta gold28Meta = gold28.getItemMeta();
		lore6.add(ChatColor.RED + "Get: XP");

		ItemStack gold32 = new ItemStack(Material.GOLD_INGOT, 32);
		ItemMeta gold32Meta = gold32.getItemMeta();
		lore7.add(ChatColor.RED + "Get: XP");

		ItemStack gold36 = new ItemStack(Material.GOLD_INGOT, 36);
		ItemMeta gold36Meta = gold36.getItemMeta();
		lore8.add(ChatColor.RED + "Get: XP");

		ItemStack gold40 = new ItemStack(Material.GOLD_INGOT, 40);
		ItemMeta gold40Meta = gold40.getItemMeta();
		lore9.add(ChatColor.RED + "Get: XP");

		ItemStack gold44 = new ItemStack(Material.GOLD_INGOT, 44);
		ItemMeta gold44Meta = gold44.getItemMeta();
		lore10.add(ChatColor.RED + "Get: XP");

		ItemStack gold48 = new ItemStack(Material.GOLD_INGOT, 48);
		ItemMeta gold48Meta = gold48.getItemMeta();
		lore11.add(ChatColor.RED + "Get: XP");

		ItemStack gold52 = new ItemStack(Material.GOLD_INGOT, 52);
		ItemMeta gold52Meta = gold52.getItemMeta();
		lore12.add(ChatColor.RED + "Get: XP");

		ItemStack gold56 = new ItemStack(Material.GOLD_INGOT, 56);
		ItemMeta gold56Meta = gold56.getItemMeta();
		lore13.add(ChatColor.RED + "Get: XP");

		ItemStack gold60 = new ItemStack(Material.GOLD_INGOT, 60);
		ItemMeta gold60Meta = gold60.getItemMeta();
		lore14.add(ChatColor.RED + "Get: XP");

		ItemStack gold64 = new ItemStack(Material.GOLD_INGOT, 64);
		ItemMeta gold64Meta = gold64.getItemMeta();
		lore15.add(ChatColor.RED + "Get: XP");
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		gold4Meta.setLore(lore);
		gold8Meta.setLore(lore1);
		gold12Meta.setLore(lore2);
		gold16Meta.setLore(lore3);
		gold20Meta.setLore(lore4);
		gold24Meta.setLore(lore5);
		gold28Meta.setLore(lore6);
		gold32Meta.setLore(lore7);
		gold36Meta.setLore(lore8);
		gold40Meta.setLore(lore9);
		gold44Meta.setLore(lore10);
		gold48Meta.setLore(lore11);
		gold52Meta.setLore(lore12);
		gold56Meta.setLore(lore13);
		gold60Meta.setLore(lore14);
		gold64Meta.setLore(lore15);
		
		gold4Meta.setDisplayName(String.format("%sGold Ingot %sx4", ChatColor.GOLD, ChatColor.DARK_RED));
		gold4.setItemMeta(gold4Meta);
		
		gold8Meta.setDisplayName(String.format("%sGold Ingot %sx8", ChatColor.GOLD, ChatColor.DARK_RED));
		gold8.setItemMeta(gold8Meta);
		
		gold12Meta.setDisplayName(String.format("%sGold Ingot %sx12", ChatColor.GOLD, ChatColor.DARK_RED));
		gold12.setItemMeta(gold12Meta);

		gold16Meta.setDisplayName(String.format("%sGold Ingot %sx16", ChatColor.GOLD, ChatColor.DARK_RED));
		gold16.setItemMeta(gold16Meta);

		gold20Meta.setDisplayName(String.format("%sGold Ingot %sx20", ChatColor.GOLD, ChatColor.DARK_RED));
		gold20.setItemMeta(gold20Meta);

		gold24Meta.setDisplayName(String.format("%sGold Ingot %sx24", ChatColor.GOLD, ChatColor.DARK_RED));
		gold24.setItemMeta(gold24Meta);

		gold28Meta.setDisplayName(String.format("%sGold Ingot %sx28", ChatColor.GOLD, ChatColor.DARK_RED));
		gold28.setItemMeta(gold28Meta);

		gold32Meta.setDisplayName(String.format("%sGold Ingot %sx32", ChatColor.GOLD, ChatColor.DARK_RED));
		gold32.setItemMeta(gold32Meta);

		gold36Meta.setDisplayName(String.format("%sGold Ingot %sx36", ChatColor.GOLD, ChatColor.DARK_RED));
		gold36.setItemMeta(gold36Meta);

		gold40Meta.setDisplayName(String.format("%sGold Ingot %sx40", ChatColor.GOLD, ChatColor.DARK_RED));
		gold40.setItemMeta(gold40Meta);

		gold44Meta.setDisplayName(String.format("%sGold Ingot %sx44", ChatColor.GOLD, ChatColor.DARK_RED));
		gold44.setItemMeta(gold44Meta);

		gold48Meta.setDisplayName(String.format("%sGold Ingot %sx48", ChatColor.GOLD, ChatColor.DARK_RED));
		gold48.setItemMeta(gold48Meta);

		gold52Meta.setDisplayName(String.format("%sGold Ingot %sx52", ChatColor.GOLD, ChatColor.DARK_RED));
		gold52.setItemMeta(gold52Meta);

		gold56Meta.setDisplayName(String.format("%sGold Ingot %sx56", ChatColor.GOLD, ChatColor.DARK_RED));
		gold56.setItemMeta(gold56Meta);

		gold60Meta.setDisplayName(String.format("%sGold Ingot %sx60", ChatColor.GOLD, ChatColor.DARK_RED));
		gold60.setItemMeta(gold60Meta);

		gold64Meta.setDisplayName(String.format("%sGold Ingot %sx64", ChatColor.GOLD, ChatColor.DARK_RED));
		gold64.setItemMeta(gold64Meta);		
		
		backMeta.setDisplayName(ChatColor.RED + "Back");
		back.setItemMeta(backMeta);
		
		buymenu.setItem(0, gold4);
		buymenu.setItem(1, gold8);
		buymenu.setItem(2, gold12);
		buymenu.setItem(3, gold16);
		buymenu.setItem(4, gold20);
		buymenu.setItem(5, gold24);
		buymenu.setItem(6, gold28);
		buymenu.setItem(7, gold32);
		buymenu.setItem(8, gold36);
		buymenu.setItem(10, gold40);
		buymenu.setItem(11, gold44);
		buymenu.setItem(12, gold48);
		buymenu.setItem(13, gold52);
		buymenu.setItem(14, gold56);
		buymenu.setItem(15, gold60);
		buymenu.setItem(16, gold64);
		buymenu.setItem(22, back);
		
		player.openInventory(buymenu);
	}
	
	@EventHandler
	public void onGoldSellMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Gold Sell Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			
			return;
		}
		
		switch(e.getCurrentItem().getAmount())
		{
		case 4:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
			break;
		case 8:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 12:
			player.closeInventory();
			player.sendMessage(String.format("%sIk %sben een %ssnol", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case 1:
			openSellMenu(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
}
	}
}