package me.stadio.xp;

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
		Inventory mainmenu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Xp Shop");
		
		ItemStack buy = new ItemStack(Material.BOOK);
		ItemMeta buyMeta = buy.getItemMeta();
		ItemStack sell = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta sellMeta = sell.getItemMeta();
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
			player.closeInventory();
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
			player.closeInventory();
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case DIAMOND:
			openDiamondBuyMenu(player.getPlayer());
			break;
		case GOLD_INGOT:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sGoldSellMenu %sOpens now", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
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
		Inventory sellmenu = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Sell Menu");
		
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
			player.closeInventory();
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case DIAMOND:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().remove(new ItemStack(Material.DIAMOND));
			break;
		case GOLD_INGOT:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sGoldSellMenu %sOpens now", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
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
		
		ItemStack diamond = new ItemStack(Material.DIAMOND, 2);
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
	public void onDiamondBuyMenuClick(InventoryClickEvent e)
	{
		if (!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Diamond Buy Menu"))
			return;
		
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getCurrentItem()==null || e.getCurrentItem().getType()==Material.AIR||!e.getCurrentItem().hasItemMeta())
		{
			player.closeInventory();
			return;
		}
		
		switch(e.getCurrentItem().getType())
		{
		case DIAMOND:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sDiamondSellMenu %sOpens now", ChatColor.RED, ChatColor.AQUA, ChatColor.RED));
			player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
			break;
		case GOLD_INGOT:
			player.closeInventory();
			player.sendMessage(String.format("%sNormal %sGoldSellMenu %sOpens now", ChatColor.RED, ChatColor.GOLD, ChatColor.RED));
			break;
		case LAVA_BUCKET:
			openGUI(player.getPlayer());
			break;
		default:
			player.closeInventory();
			break;
		}
	}
}