package me.stadio;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{

	@Override
	public void onEnable()
	{
		getLogger().info("The Stadio XPShop is Enabled!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	private void openGUI(Player player)
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "XP Shop");
	}
	
}
