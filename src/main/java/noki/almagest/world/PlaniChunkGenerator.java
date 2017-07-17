package noki.almagest.world;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import noki.almagest.AlmagestCore;
import noki.almagest.AlmagestData;
import noki.almagest.entity.EntityMira;
import noki.almagest.registry.ModBlocks;


public class PlaniChunkGenerator implements IChunkGenerator {
	
	private World world;
	private Random random;

	public PlaniChunkGenerator(World world, long seed) {
		
		this.world = world;
		this.random = new Random(seed);
				
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Chunk generateChunk(int chunkX, int chunkZ) {
		
//		AlmagestCore.log("chunkX/chunkZ is %s/%s.", chunkX, chunkZ);
		
		ChunkPrimer chunkPrimer = new ChunkPrimer();
		
		for(int height=1; height<=88; height++) {
			for(int width=0; width<16; width++) {
				for(int depth=0; depth<16; depth++) {
					chunkPrimer.setBlockState(width, height, depth, Blocks.WATER.getDefaultState());
				}
			}
		}
		
		// central circle.
		if(-3 <= chunkX && chunkX <= 3 && -3 <= chunkZ && chunkZ <= 3) {
			for(int height=1; height<=88; height++) {
				for(int width=0; width<16; width++) {
					for(int depth=0; depth<16; depth++) {
	//					chunkPrimer.setBlockState(width, 88, depth, AlmagestData.blockStar.getStateFromMeta(0));
						// distant from 0:0.
						for(int layer=3; layer>=0; layer--) {
							if(Math.pow(chunkX*16+width, 2)+Math.pow(chunkZ*16+depth, 2)
									<= Math.pow((layer+1)*10, 2)) {
								chunkPrimer.setBlockState(width, height, depth, ModBlocks.ATLAS_STAR.getStateFromMeta(3-layer));
							}
						}
					}
				}
			}
		}
		// other areas.
		else {
			int times = this.getRoundedNormalDist(0, 1);
			for(int i=0; i<times; i++) {
//				int type = this.random.nextInt(7);
				int type = 3;
				double radius = this.getRoundedNormalDist(1, 1);
				int radiusCeil = (int)Math.ceil(radius);
				int x = this.random.nextInt(15-radiusCeil*2) + radiusCeil;
				int z = this.random.nextInt(15-radiusCeil*2) + radiusCeil;
				int y = this.random.nextInt(88-radiusCeil) +  radiusCeil;
				for(int width=-radiusCeil; width<=radiusCeil; width++) {
					for(int depth=-radiusCeil; depth<=radiusCeil; depth++) {
						for(int height=-radiusCeil; height<=radiusCeil; height++) {
							if(width*width+depth*depth+height*height <= radius*radius*0.7) {
								chunkPrimer.setBlockState(x+width, y+height, z+depth, ModBlocks.ATLAS_STAR.getStateFromMeta(type));
//								AlmagestCore.log("type/radius/x/y/z is %s/%s/%s/%s/%s.", type, radius, x+width, y+height, z+depth);
							}
						}					
					}	
				}
			}
		}
		
		Chunk chunk = new Chunk(this.world, chunkPrimer, chunkX, chunkZ);
		byte[] abyte = chunk.getBiomeArray();
		
		for(int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte)88;	//plani biome.
		}
		
		chunk.generateSkylightMap();
		
//		this.populate(chunkX, chunkZ);
		
		return chunk;
		
	}
	
	@Override
	public void populate(int chunkX, int chunkZ) {
		
		if(chunkX == 0 && chunkZ == 0) {
			EntityMira entity = new EntityMira(this.world);
			entity.setPosition(0, 91, 0);
			this.world.spawnEntity(entity);
		}
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
		
        return this.world.getBiome(pos).getSpawnableList(type);
        
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		
		return false;
		
	}
	
/*	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
		
		return null;
		
	}*/
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
		// nothing to do.
		
	}
	
	private double getNormalDist(double mean, double deviation) {
		
		double res = this.random.nextGaussian();
		AlmagestCore.log("res/%s.", res);
		res = res*deviation + mean;
		return res;
		
	}
	
	private int getRoundedNormalDist(double mean, double deviation) {
		
		return (int)Math.round(this.getNormalDist(mean, deviation));
		
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInsideStructure(World p_193414_1_, String p_193414_2_, BlockPos p_193414_3_) {
		
		return false;
		
	}
	
}