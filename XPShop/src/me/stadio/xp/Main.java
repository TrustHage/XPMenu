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
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Diamond Buy Menu");
		
		ItemStack diamond4 = new ItemStack(Material.DIAMOND, 4);
		ItemMeta diamond4Meta = diamond4.getItemMeta();
		
		ItemStack diamond8 = new ItemStack(Material.DIAMOND, 8);
		ItemMeta diamond8Meta = diamond8.getItemMeta();
		
		ItemStack diamond12 = new ItemStack(Material.DIAMOND, 12);
		ItemMeta diamond12Meta = diamond12.getItemMeta();
		
		ItemStack diamond16 = new ItemStack(Material.DIAMOND, 16);
		ItemMeta diamond16Meta = diamond16.getItemMeta();

		ItemStack diamond20 = new ItemStack(Material.DIAMOND, 20);
		ItemMeta diamond20Meta = diamond20.getItemMeta();

		ItemStack diamond24 = new ItemStack(Material.DIAMOND, 24);
		ItemMeta diamond24Meta = diamond24.getItemMeta();

		ItemStack diamond28 = new ItemStack(Material.DIAMOND, 28);
		ItemMeta diamond28Meta = diamond28.getItemMeta();

		ItemStack diamond32 = new ItemStack(Material.DIAMOND, 32);
		ItemMeta diamond32Meta = diamond32.getItemMeta();

		ItemStack diamond36 = new ItemStack(Material.DIAMOND, 36);
		ItemMeta diamond36Meta = diamond36.getItemMeta();

		ItemStack diamond40 = new ItemStack(Material.DIAMOND, 40);
		ItemMeta diamond40Meta = diamond40.getItemMeta();

		ItemStack diamond44 = new ItemStack(Material.DIAMOND, 44);
		ItemMeta diamond44Meta = diamond44.getItemMeta();

		ItemStack diamond48 = new ItemStack(Material.DIAMOND, 48);
		ItemMeta diamond48Meta = diamond48.getItemMeta();

		ItemStack diamond52 = new ItemStack(Material.DIAMOND, 52);
		ItemMeta diamond52Meta = diamond52.getItemMeta();

		ItemStack diamond56 = new ItemStack(Material.DIAMOND, 56);
		ItemMeta diamond56Meta = diamond56.getItemMeta();

		ItemStack diamond60 = new ItemStack(Material.DIAMOND, 60);
		ItemMeta diamond60Meta = diamond60.getItemMeta();

		ItemStack diamond64 = new ItemStack(Material.DIAMOND, 64);
		ItemMeta diamond64Meta = diamond64.getItemMeta();
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		diamond4Meta.setDisplayName(String.format("%sDiamonds %sx4", ChatColor.AQUA, ChatColor.RED));
		diamond4.setItemMeta(diamond4Meta);
		
		diamond8Meta.setDisplayName(String.format("%sDiamonds %sx8", ChatColor.AQUA, ChatColor.RED));
		diamond8.setItemMeta(diamond8Meta);
		
		diamond12Meta.setDisplayName(String.format("%sDiamonds %sx12", ChatColor.AQUA, ChatColor.RED));
		diamond12.setItemMeta(diamond12Meta);

		diamond16Meta.setDisplayName(String.format("%sDiamonds %sx16", ChatColor.AQUA, ChatColor.RED));
		diamond16.setItemMeta(diamond16Meta);

		diamond20Meta.setDisplayName(String.format("%sDiamonds %sx20", ChatColor.AQUA, ChatColor.RED));
		diamond20.setItemMeta(diamond20Meta);

		diamond24Meta.setDisplayName(String.format("%sDiamonds %sx24", ChatColor.AQUA, ChatColor.RED));
		diamond24.setItemMeta(diamond24Meta);

		diamond28Meta.setDisplayName(String.format("%sDiamonds %sx28", ChatColor.AQUA, ChatColor.RED));
		diamond28.setItemMeta(diamond28Meta);

		diamond32Meta.setDisplayName(String.format("%sDiamonds %sx32", ChatColor.AQUA, ChatColor.RED));
		diamond32.setItemMeta(diamond32Meta);

		diamond36Meta.setDisplayName(String.format("%sDiamonds %sx36", ChatColor.AQUA, ChatColor.RED));
		diamond36.setItemMeta(diamond36Meta);

		diamond40Meta.setDisplayName(String.format("%sDiamonds %sx40", ChatColor.AQUA, ChatColor.RED));
		diamond40.setItemMeta(diamond40Meta);

		diamond44Meta.setDisplayName(String.format("%sDiamonds %sx44", ChatColor.AQUA, ChatColor.RED));
		diamond44.setItemMeta(diamond44Meta);

		diamond48Meta.setDisplayName(String.format("%sDiamonds %sx48", ChatColor.AQUA, ChatColor.RED));
		diamond48.setItemMeta(diamond48Meta);

		diamond52Meta.setDisplayName(String.format("%sDiamonds %sx52", ChatColor.AQUA, ChatColor.RED));
		diamond52.setItemMeta(diamond52Meta);

		diamond56Meta.setDisplayName(String.format("%sDiamonds %sx56", ChatColor.AQUA, ChatColor.RED));
		diamond56.setItemMeta(diamond56Meta);

		diamond60Meta.setDisplayName(String.format("%sDiamonds %sx60", ChatColor.AQUA, ChatColor.RED));
		diamond60.setItemMeta(diamond60Meta);

		diamond64Meta.setDisplayName(String.format("%sDiamonds %sx64", ChatColor.AQUA, ChatColor.RED));
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
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Gold Buy Menu");
		
		ItemStack gold4 = new ItemStack(Material.GOLD_INGOT, 4);
		ItemMeta gold4Meta = gold4.getItemMeta();
		
		ItemStack gold8 = new ItemStack(Material.GOLD_INGOT, 8);
		ItemMeta gold8Meta = gold8.getItemMeta();
		
		ItemStack gold12 = new ItemStack(Material.GOLD_INGOT, 12);
		ItemMeta gold12Meta = gold12.getItemMeta();
		
		ItemStack gold16 = new ItemStack(Material.GOLD_INGOT, 16);
		ItemMeta gold16Meta = gold16.getItemMeta();

		ItemStack gold20 = new ItemStack(Material.GOLD_INGOT, 20);
		ItemMeta gold20Meta = gold20.getItemMeta();

		ItemStack gold24 = new ItemStack(Material.GOLD_INGOT, 24);
		ItemMeta gold24Meta = gold24.getItemMeta();

		ItemStack gold28 = new ItemStack(Material.GOLD_INGOT, 28);
		ItemMeta gold28Meta = gold28.getItemMeta();

		ItemStack gold32 = new ItemStack(Material.GOLD_INGOT, 32);
		ItemMeta gold32Meta = gold32.getItemMeta();

		ItemStack gold36 = new ItemStack(Material.GOLD_INGOT, 36);
		ItemMeta gold36Meta = gold36.getItemMeta();

		ItemStack gold40 = new ItemStack(Material.GOLD_INGOT, 40);
		ItemMeta gold40Meta = gold40.getItemMeta();

		ItemStack gold44 = new ItemStack(Material.GOLD_INGOT, 44);
		ItemMeta gold44Meta = gold44.getItemMeta();

		ItemStack gold48 = new ItemStack(Material.GOLD_INGOT, 48);
		ItemMeta gold48Meta = gold48.getItemMeta();

		ItemStack gold52 = new ItemStack(Material.GOLD_INGOT, 52);
		ItemMeta gold52Meta = gold52.getItemMeta();

		ItemStack gold56 = new ItemStack(Material.GOLD_INGOT, 56);
		ItemMeta gold56Meta = gold56.getItemMeta();

		ItemStack gold60 = new ItemStack(Material.GOLD_INGOT, 60);
		ItemMeta gold60Meta = gold60.getItemMeta();

		ItemStack gold64 = new ItemStack(Material.GOLD_INGOT, 64);
		ItemMeta gold64Meta = gold64.getItemMeta();
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		gold4Meta.setDisplayName(String.format("%sgold %sx4", ChatColor.GOLD, ChatColor.RED));
		gold4.setItemMeta(gold4Meta);
		
		gold8Meta.setDisplayName(String.format("%sgold %sx8", ChatColor.GOLD, ChatColor.RED));
		gold8.setItemMeta(gold8Meta);
		
		gold12Meta.setDisplayName(String.format("%sgold %sx12", ChatColor.GOLD, ChatColor.RED));
		gold12.setItemMeta(gold12Meta);

		gold16Meta.setDisplayName(String.format("%sgold %sx16", ChatColor.GOLD, ChatColor.RED));
		gold16.setItemMeta(gold16Meta);

		gold20Meta.setDisplayName(String.format("%sgold %sx20", ChatColor.GOLD, ChatColor.RED));
		gold20.setItemMeta(gold20Meta);

		gold24Meta.setDisplayName(String.format("%sgold %sx24", ChatColor.GOLD, ChatColor.RED));
		gold24.setItemMeta(gold24Meta);

		gold28Meta.setDisplayName(String.format("%sgold %sx28", ChatColor.GOLD, ChatColor.RED));
		gold28.setItemMeta(gold28Meta);

		gold32Meta.setDisplayName(String.format("%sgold %sx32", ChatColor.GOLD, ChatColor.RED));
		gold32.setItemMeta(gold32Meta);

		gold36Meta.setDisplayName(String.format("%sgold %sx36", ChatColor.GOLD, ChatColor.RED));
		gold36.setItemMeta(gold36Meta);

		gold40Meta.setDisplayName(String.format("%sgold %sx40", ChatColor.GOLD, ChatColor.RED));
		gold40.setItemMeta(gold40Meta);

		gold44Meta.setDisplayName(String.format("%sgold %sx44", ChatColor.GOLD, ChatColor.RED));
		gold44.setItemMeta(gold44Meta);

		gold48Meta.setDisplayName(String.format("%sgold %sx48", ChatColor.GOLD, ChatColor.RED));
		gold48.setItemMeta(gold48Meta);

		gold52Meta.setDisplayName(String.format("%sgold %sx52", ChatColor.GOLD, ChatColor.RED));
		gold52.setItemMeta(gold52Meta);

		gold56Meta.setDisplayName(String.format("%sgold %sx56", ChatColor.GOLD, ChatColor.RED));
		gold56.setItemMeta(gold56Meta);

		gold60Meta.setDisplayName(String.format("%sgold %sx60", ChatColor.GOLD, ChatColor.RED));
		gold60.setItemMeta(gold60Meta);

		gold64Meta.setDisplayName(String.format("%sgold %sx64", ChatColor.GOLD, ChatColor.RED));
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
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Diamond Sell Menu");
		
		ItemStack diamond4 = new ItemStack(Material.DIAMOND, 4);
		ItemMeta diamond4Meta = diamond4.getItemMeta();
		
		ItemStack diamond8 = new ItemStack(Material.DIAMOND, 8);
		ItemMeta diamond8Meta = diamond8.getItemMeta();
		
		ItemStack diamond12 = new ItemStack(Material.DIAMOND, 12);
		ItemMeta diamond12Meta = diamond12.getItemMeta();
		
		ItemStack diamond16 = new ItemStack(Material.DIAMOND, 16);
		ItemMeta diamond16Meta = diamond16.getItemMeta();

		ItemStack diamond20 = new ItemStack(Material.DIAMOND, 20);
		ItemMeta diamond20Meta = diamond20.getItemMeta();

		ItemStack diamond24 = new ItemStack(Material.DIAMOND, 24);
		ItemMeta diamond24Meta = diamond24.getItemMeta();

		ItemStack diamond28 = new ItemStack(Material.DIAMOND, 28);
		ItemMeta diamond28Meta = diamond28.getItemMeta();

		ItemStack diamond32 = new ItemStack(Material.DIAMOND, 32);
		ItemMeta diamond32Meta = diamond32.getItemMeta();

		ItemStack diamond36 = new ItemStack(Material.DIAMOND, 36);
		ItemMeta diamond36Meta = diamond36.getItemMeta();

		ItemStack diamond40 = new ItemStack(Material.DIAMOND, 40);
		ItemMeta diamond40Meta = diamond40.getItemMeta();

		ItemStack diamond44 = new ItemStack(Material.DIAMOND, 44);
		ItemMeta diamond44Meta = diamond44.getItemMeta();

		ItemStack diamond48 = new ItemStack(Material.DIAMOND, 48);
		ItemMeta diamond48Meta = diamond48.getItemMeta();

		ItemStack diamond52 = new ItemStack(Material.DIAMOND, 52);
		ItemMeta diamond52Meta = diamond52.getItemMeta();

		ItemStack diamond56 = new ItemStack(Material.DIAMOND, 56);
		ItemMeta diamond56Meta = diamond56.getItemMeta();

		ItemStack diamond60 = new ItemStack(Material.DIAMOND, 60);
		ItemMeta diamond60Meta = diamond60.getItemMeta();

		ItemStack diamond64 = new ItemStack(Material.DIAMOND, 64);
		ItemMeta diamond64Meta = diamond64.getItemMeta();
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		diamond4Meta.setDisplayName(String.format("%sDiamonds %sx4", ChatColor.AQUA, ChatColor.RED));
		diamond4.setItemMeta(diamond4Meta);
		
		diamond8Meta.setDisplayName(String.format("%sDiamonds %sx8", ChatColor.AQUA, ChatColor.RED));
		diamond8.setItemMeta(diamond8Meta);
		
		diamond12Meta.setDisplayName(String.format("%sDiamonds %sx12", ChatColor.AQUA, ChatColor.RED));
		diamond12.setItemMeta(diamond12Meta);

		diamond16Meta.setDisplayName(String.format("%sDiamonds %sx16", ChatColor.AQUA, ChatColor.RED));
		diamond16.setItemMeta(diamond16Meta);

		diamond20Meta.setDisplayName(String.format("%sDiamonds %sx20", ChatColor.AQUA, ChatColor.RED));
		diamond20.setItemMeta(diamond20Meta);

		diamond24Meta.setDisplayName(String.format("%sDiamonds %sx24", ChatColor.AQUA, ChatColor.RED));
		diamond24.setItemMeta(diamond24Meta);

		diamond28Meta.setDisplayName(String.format("%sDiamonds %sx28", ChatColor.AQUA, ChatColor.RED));
		diamond28.setItemMeta(diamond28Meta);

		diamond32Meta.setDisplayName(String.format("%sDiamonds %sx32", ChatColor.AQUA, ChatColor.RED));
		diamond32.setItemMeta(diamond32Meta);

		diamond36Meta.setDisplayName(String.format("%sDiamonds %sx36", ChatColor.AQUA, ChatColor.RED));
		diamond36.setItemMeta(diamond36Meta);

		diamond40Meta.setDisplayName(String.format("%sDiamonds %sx40", ChatColor.AQUA, ChatColor.RED));
		diamond40.setItemMeta(diamond40Meta);

		diamond44Meta.setDisplayName(String.format("%sDiamonds %sx44", ChatColor.AQUA, ChatColor.RED));
		diamond44.setItemMeta(diamond44Meta);

		diamond48Meta.setDisplayName(String.format("%sDiamonds %sx48", ChatColor.AQUA, ChatColor.RED));
		diamond48.setItemMeta(diamond48Meta);

		diamond52Meta.setDisplayName(String.format("%sDiamonds %sx52", ChatColor.AQUA, ChatColor.RED));
		diamond52.setItemMeta(diamond52Meta);

		diamond56Meta.setDisplayName(String.format("%sDiamonds %sx56", ChatColor.AQUA, ChatColor.RED));
		diamond56.setItemMeta(diamond56Meta);

		diamond60Meta.setDisplayName(String.format("%sDiamonds %sx60", ChatColor.AQUA, ChatColor.RED));
		diamond60.setItemMeta(diamond60Meta);

		diamond64Meta.setDisplayName(String.format("%sDiamonds %sx64", ChatColor.AQUA, ChatColor.RED));
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
		Inventory buymenu = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Gold Sell Menu");
		
		ItemStack gold4 = new ItemStack(Material.GOLD_INGOT, 4);
		ItemMeta gold4Meta = gold4.getItemMeta();
		
		ItemStack gold8 = new ItemStack(Material.GOLD_INGOT, 8);
		ItemMeta gold8Meta = gold8.getItemMeta();
		
		ItemStack gold12 = new ItemStack(Material.GOLD_INGOT, 12);
		ItemMeta gold12Meta = gold12.getItemMeta();
		
		ItemStack gold16 = new ItemStack(Material.GOLD_INGOT, 16);
		ItemMeta gold16Meta = gold16.getItemMeta();

		ItemStack gold20 = new ItemStack(Material.GOLD_INGOT, 20);
		ItemMeta gold20Meta = gold20.getItemMeta();

		ItemStack gold24 = new ItemStack(Material.GOLD_INGOT, 24);
		ItemMeta gold24Meta = gold24.getItemMeta();

		ItemStack gold28 = new ItemStack(Material.GOLD_INGOT, 28);
		ItemMeta gold28Meta = gold28.getItemMeta();

		ItemStack gold32 = new ItemStack(Material.GOLD_INGOT, 32);
		ItemMeta gold32Meta = gold32.getItemMeta();

		ItemStack gold36 = new ItemStack(Material.GOLD_INGOT, 36);
		ItemMeta gold36Meta = gold36.getItemMeta();

		ItemStack gold40 = new ItemStack(Material.GOLD_INGOT, 40);
		ItemMeta gold40Meta = gold40.getItemMeta();

		ItemStack gold44 = new ItemStack(Material.GOLD_INGOT, 44);
		ItemMeta gold44Meta = gold44.getItemMeta();

		ItemStack gold48 = new ItemStack(Material.GOLD_INGOT, 48);
		ItemMeta gold48Meta = gold48.getItemMeta();

		ItemStack gold52 = new ItemStack(Material.GOLD_INGOT, 52);
		ItemMeta gold52Meta = gold52.getItemMeta();

		ItemStack gold56 = new ItemStack(Material.GOLD_INGOT, 56);
		ItemMeta gold56Meta = gold56.getItemMeta();

		ItemStack gold60 = new ItemStack(Material.GOLD_INGOT, 60);
		ItemMeta gold60Meta = gold60.getItemMeta();

		ItemStack gold64 = new ItemStack(Material.GOLD_INGOT, 64);
		ItemMeta gold64Meta = gold64.getItemMeta();
		
		ItemStack back = new ItemStack(Material.LAVA_BUCKET,1);
		ItemMeta backMeta = back.getItemMeta();
		
		gold4Meta.setDisplayName(String.format("%sgold %sx4", ChatColor.GOLD, ChatColor.RED));
		gold4.setItemMeta(gold4Meta);
		
		gold8Meta.setDisplayName(String.format("%sgold %sx8", ChatColor.GOLD, ChatColor.RED));
		gold8.setItemMeta(gold8Meta);
		
		gold12Meta.setDisplayName(String.format("%sgold %sx12", ChatColor.GOLD, ChatColor.RED));
		gold12.setItemMeta(gold12Meta);

		gold16Meta.setDisplayName(String.format("%sgold %sx16", ChatColor.GOLD, ChatColor.RED));
		gold16.setItemMeta(gold16Meta);

		gold20Meta.setDisplayName(String.format("%sgold %sx20", ChatColor.GOLD, ChatColor.RED));
		gold20.setItemMeta(gold20Meta);

		gold24Meta.setDisplayName(String.format("%sgold %sx24", ChatColor.GOLD, ChatColor.RED));
		gold24.setItemMeta(gold24Meta);

		gold28Meta.setDisplayName(String.format("%sgold %sx28", ChatColor.GOLD, ChatColor.RED));
		gold28.setItemMeta(gold28Meta);

		gold32Meta.setDisplayName(String.format("%sgold %sx32", ChatColor.GOLD, ChatColor.RED));
		gold32.setItemMeta(gold32Meta);

		gold36Meta.setDisplayName(String.format("%sgold %sx36", ChatColor.GOLD, ChatColor.RED));
		gold36.setItemMeta(gold36Meta);

		gold40Meta.setDisplayName(String.format("%sgold %sx40", ChatColor.GOLD, ChatColor.RED));
		gold40.setItemMeta(gold40Meta);

		gold44Meta.setDisplayName(String.format("%sgold %sx44", ChatColor.GOLD, ChatColor.RED));
		gold44.setItemMeta(gold44Meta);

		gold48Meta.setDisplayName(String.format("%sgold %sx48", ChatColor.GOLD, ChatColor.RED));
		gold48.setItemMeta(gold48Meta);

		gold52Meta.setDisplayName(String.format("%sgold %sx52", ChatColor.GOLD, ChatColor.RED));
		gold52.setItemMeta(gold52Meta);

		gold56Meta.setDisplayName(String.format("%sgold %sx56", ChatColor.GOLD, ChatColor.RED));
		gold56.setItemMeta(gold56Meta);

		gold60Meta.setDisplayName(String.format("%sgold %sx60", ChatColor.GOLD, ChatColor.RED));
		gold60.setItemMeta(gold60Meta);

		gold64Meta.setDisplayName(String.format("%sgold %sx64", ChatColor.GOLD, ChatColor.RED));
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