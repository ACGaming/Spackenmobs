package mod.acgaming.spackenmobs.items;
import mod.acgaming.spackenmobs.Spackenmobs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRAMOnAStick extends ItemCarrotOnAStick
{
    public ItemRAMOnAStick()
    {
        setRegistryName("ram_on_a_stick");
        setUnlocalizedName(Spackenmobs.MODID + ".ram_on_a_stick");
    }
    
    @SideOnly(Side.CLIENT)
    public void initModel()
    {
    	ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}