package mod.acgaming.spackenmobs.items;
import mod.acgaming.spackenmobs.Spackenmobs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRAM extends Item
{
    public ItemRAM()
    {
        setRegistryName("ram");
        setUnlocalizedName(Spackenmobs.MODID + ".ram");
        setCreativeTab(CreativeTabs.MISC);
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel()
    {
    	ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}