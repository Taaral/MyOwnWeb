package SkillMyOwnWeb;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.classes.HeroClass;
import com.herocraftonline.heroes.characters.classes.HeroClass.ExperienceType;
import com.herocraftonline.heroes.characters.effects.EffectType;
import com.herocraftonline.heroes.characters.skill.PassiveSkill;
import com.herocraftonline.heroes.characters.skill.Skill;
import com.herocraftonline.heroes.characters.skill.SkillConfigManager;
import com.herocraftonline.heroes.characters.skill.SkillSetting;
import com.herocraftonline.heroes.characters.skill.SkillType;
import com.herocraftonline.heroes.util.Messaging;
import com.herocraftonline.heroes.util.Properties;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;


/**
 * Created with IntelliJ IDEA.
 * User: nicolaslachance
 * Date: 2013-05-05
 * Time: 9:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class SkillMyOwnWeb extends PassiveSkill
{
    private Heroes plugin;
    public SkillMyOwnWeb(Heroes plugin)
    {
        super(plugin, "MyOwnWeb");
        this.plugin = plugin;
        setDescription("No web shall resist you.");
        setArgumentRange(0, 0);
        setTypes(new SkillType[] { SkillType.MOVEMENT, SkillType.BUFF });
        setEffectTypes(new EffectType[] { EffectType.BENEFICIAL });
        Bukkit.getServer().getPluginManager().registerEvents(new SkillWebListener(this), plugin);
        System.out.println("Constructeur");
    }

    public String getDescription(Hero hero)
    {
        return getDescription();
    }

    public class SkillWebListener
            implements Listener
    {
        private final Skill skill;

        public SkillWebListener(Skill skill)
        {
            this.skill = skill;
        }

        @EventHandler(priority=EventPriority.LOWEST)
        public void onPlayerMoveEvent(PlayerMoveEvent event)
        {
            if (plugin.getCharacterManager().getHero(event.getPlayer()).canUseSkill(skill)) {
                Player player = event.getPlayer();
                Hero hero = SkillMyOwnWeb.this.plugin.getCharacterManager().getHero(player);

                Location loc;
                Location temp = player.getLocation();
                int radius = 5;

                for (int x = -(radius); x <= radius; x++)
                {
                    for (int y = -(radius); y <= radius; y++)
                    {

                        for (int z = -(radius); z <= radius; z++)
                        {

                            loc = temp.getBlock().getRelative(x, y, z).getLocation();

                            if (loc.getBlock().getType().equals(Material.WEB))
                            {
                                player.sendBlockChange(loc, Material.AIR, (byte) 0);
                            }
                        }
                    }
                }
            }

        }


    }
}
