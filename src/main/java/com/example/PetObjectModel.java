

//Code adapted and modified from Justin Ead (Jebrim)'s JebScapeActor class
package com.example;

import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ClientTick;

public class PetObjectModel
{
	private Client client;
	private RuneLiteObject rlObject;

	private class Target
	{
		public WorldPoint wpDest;
		public LocalPoint lpDest;
		public int currentDistance;
	}

	private final int MAX_TARGET_QUEUE_SIZE = 10;
	private Target[] targetQueue = new Target[MAX_TARGET_QUEUE_SIZE];
	private int cTargetIndex;
	public int targetQueueSize;
	private int lastDistance;
	public int distance;
	private PetData petData;

	private enum POSE_ANIM
	{
		IDLE,
		WALK,
		RUN,
	}

	public Animation[] animationPoses = new Animation[3];
	
	
	public void init(Client client,PetData petData)
	{
		this.client = client;

		this.rlObject = new RuneLiteObject(client)
		{
			@Override
			public Model getModel() {
				Model m = super.getModel();
				if (PetObjectModel.this.petData.getScale() != -1)
				{
					m = m.scale(PetObjectModel.this.petData.getScale(), PetObjectModel.this.petData.getScale(), PetObjectModel.this.petData.getScale());
				}
				return m;
			}
		};


		this.rlObject.setWorldView(-1);
		this.petData = petData;
		for (int i = 0; i < MAX_TARGET_QUEUE_SIZE; i++)
		{
			targetQueue[i] = new Target();
		}
	}

	public int getPetAttkAnim()
	{
		return petData.getAttkAnim();
	}

	public int getPetAttkAnimFrames()
	{
		return petData.getAttkAnimFrames();
	}

	public int getSize()
	{
		return petData.getSize();
	}

	public void setPetData(PetData petData)
	{
		this.petData = petData;
	}

	public void setModel(Model model)
	{
		rlObject.setModel(model);
	}


	public RuneLiteObject getRlObject()
	{
		return rlObject;
	}

	public WorldArea getWorldArea()
	{
		if (petData.getSize() == 2)
		{
			return new WorldArea(WorldPoint.fromLocal(client,new LocalPoint(rlObject.getLocation().getX() - 64,rlObject.getLocation().getY() - 64)),2,2);
		}
		else
		{
			return new WorldArea(WorldPoint.fromLocal(client,rlObject.getLocation()),1,1);
		}

	}
	
	public void spawn(WorldPoint position, int jauOrientation, int size)
	{
		LocalPoint localPosition = LocalPoint.fromWorld(client, position);

		if (localPosition != null && client.getPlane() == position.getPlane())
		{
			rlObject.setLocation(localPosition, position.getPlane());
		}
		else
		{
			rlObject.setLocation(new LocalPoint(0, 0), client.getPlane());
		}
		rlObject.setOrientation(jauOrientation);
		rlObject.setShouldLoop(true);
		rlObject.setActive(true);
		this.lastDistance = 0;
		this.cTargetIndex = 0;
		this.targetQueueSize = 0;
	}
	
	public void despawn()
	{
		rlObject.setActive(false);
		this.lastDistance = 0;
		this.cTargetIndex = 0;
		this.targetQueueSize = 0;
	}

	//add 180, 90R and 90L animation support look at snakling data
	public void setPoseAnimations(int idle, int walk, int run)
	{
		this.animationPoses[POSE_ANIM.IDLE.ordinal()] = client.loadAnimation(idle);//7125
		this.animationPoses[POSE_ANIM.WALK.ordinal()] = client.loadAnimation(walk);//7124
		this.animationPoses[POSE_ANIM.RUN.ordinal()] = client.loadAnimation(run);
	}
	
	public WorldPoint getWorldLocation()
	{
		return targetQueueSize > 0 ? targetQueue[cTargetIndex].wpDest : WorldPoint.fromLocal(client, rlObject.getLocation());
	}

	public void setAnimation(Animation animation)
	{
		rlObject.setAnimation(animation);
	}

	public LocalPoint getLocalLocation()
	{
		return rlObject.getLocation();
	}
	
	public boolean isActive()
	{
		return rlObject.isActive();
	}

	public int getOrientation()
	{
		return rlObject.getOrientation();
	}

	
	// moveTo() adds target movement states to the queue for later per-frame updating for rendering in onClientTick()
	public void moveTo(WorldPoint worldPosition, int jauOrientation, int size)
	{

		if (!rlObject.isActive())
		{
			spawn(worldPosition, jauOrientation, size);
		}

		LocalPoint localPosition = LocalPoint.fromWorld(client, worldPosition);
		if (size == 2)
		{
			localPosition = new LocalPoint(localPosition.getX() + 64,localPosition.getY() + 64);
			worldPosition = WorldPoint.fromLocal(client,localPosition);
		}


		// just clear the queue and move immediately to the destination if many ticks behind
		if (targetQueueSize >= MAX_TARGET_QUEUE_SIZE - 2)
		{
			targetQueueSize = 0;
		}


		int prevTargetIndex = (cTargetIndex + targetQueueSize - 1) % MAX_TARGET_QUEUE_SIZE;
		int newTargetIndex = (cTargetIndex + targetQueueSize) % MAX_TARGET_QUEUE_SIZE;

		if (localPosition == null)
		{
			return;
		}

		WorldPoint prevWorldPosition;
		if (targetQueueSize++ > 0)
		{
			prevWorldPosition = targetQueue[prevTargetIndex].wpDest;
		}
		else
		{
			prevWorldPosition = WorldPoint.fromLocal(client,rlObject.getLocation());
		}

		int distance = prevWorldPosition.distanceTo(worldPosition);

		this.targetQueue[newTargetIndex].wpDest = worldPosition;
		this.targetQueue[newTargetIndex].lpDest = localPosition;
		this.targetQueue[newTargetIndex].currentDistance = distance;

	}

	// onClientTick() updates the per-frame state needed for rendering actor movement
	public boolean onClientTick(ClientTick clientTick, int orentation)
	{
		if (rlObject.isActive())
		{
			boolean rotationDone = rotateObject(orentation);

			if (targetQueueSize > 0)
			{
				int targetPlane = targetQueue[cTargetIndex].wpDest.getPlane();

				LocalPoint targetPosition = targetQueue[cTargetIndex].lpDest;


				if (client.getPlane() != targetPlane || targetPosition == null || !targetPosition.isInScene())
				{
					// this actor is no longer in a visible area on our client, so let's despawn it
					despawn();
					return false;
				}

				//apply animation if move-speed / distance has changed                         //this is the attack animation ID
				if (lastDistance != targetQueue[cTargetIndex].currentDistance && getRlObject().getAnimation().getId() !=  this.petData.getAttkAnim())
				{
					int distance = targetQueue[cTargetIndex].currentDistance;

					// we don't want to go beyond run (speed of 2)
					rlObject.setAnimation(distance > 2 ? null : animationPoses[distance]);

					if (rlObject.getAnimation() == null)
					{
						rlObject.setAnimation(animationPoses[1]);
					}

				}

				this.lastDistance = targetQueue[cTargetIndex].currentDistance;

				LocalPoint currentPosition = rlObject.getLocation();
				int dx = targetPosition.getX()  - currentPosition.getX();
				int dy = targetPosition.getY() - currentPosition.getY();

				
				// are we not where we need to be?
				if (dx != 0 || dy != 0)
				{

					int speed = lastDistance < 2 ? 4 : 8;

					if (speed > 0)
					{
						// only use the delta if it won't send up past the target
						if (Math.abs(dx) > speed)
						{
							dx = Integer.signum(dx) * speed;
						}

						if (Math.abs(dy) > speed)
						{
							dy = Integer.signum(dy) * speed;
						}

					}


					LocalPoint newLocation = new LocalPoint(currentPosition.getX() + dx , currentPosition.getY() + dy);

					int zOff = Perspective.getTileHeight(client,rlObject.getLocation(),rlObject.getLevel());
					rlObject.setLocation(newLocation, targetPlane);
					rlObject.setZ(zOff);

					dx = targetPosition.getX() - rlObject.getLocation().getX();
					dy = targetPosition.getY() - rlObject.getLocation().getY();
				}



				// have we arrived at our target?
				if (dx == 0 && dy == 0 && rotationDone)
				{
					// if so, pull out the next target
					cTargetIndex = (cTargetIndex + 1) % MAX_TARGET_QUEUE_SIZE;
					targetQueueSize--;
				}

			}

			return true;
		}
		
		return false;
	}

	public boolean rotateObject(int orentation)
	{

		final int JAU_FULL_ROTATION = 2048;
		int targetOrientation = orentation;
		int currentOrientation = rlObject.getOrientation();

		int dJau = (targetOrientation - currentOrientation) % JAU_FULL_ROTATION;

		if (dJau != 0)
		{
			final int JAU_HALF_ROTATION = 1024;
			final int JAU_TURN_SPEED = 32;
			int dJauCW = Math.abs(dJau);

			if (dJauCW > JAU_HALF_ROTATION)// use the shortest turn
			{
				dJau = (currentOrientation - targetOrientation) % JAU_FULL_ROTATION;
			}

			else if (dJauCW == JAU_HALF_ROTATION)// always turn right when turning around
			{
				dJau = dJauCW;
			}


			// only use the delta if it won't send up past the target
			if (Math.abs(dJau) > JAU_TURN_SPEED)
			{
				dJau = Integer.signum(dJau) * JAU_TURN_SPEED;
			}


			int newOrientation = (JAU_FULL_ROTATION + rlObject.getOrientation() + dJau) % JAU_FULL_ROTATION;

			rlObject.setOrientation(newOrientation);

			dJau = (targetOrientation - newOrientation) % JAU_FULL_ROTATION;
		}

		return dJau == 0;
	}

	static int radToJau(double a)
	{
		int j = (int) Math.round(a / Perspective.UNIT);
		return j & 2047;
	}

}
