# StackableSpawners

Stack spawners together to upgrade how many mobs are spawned next time. You can right click on a spawner with your empty hand to check its level and type.

For example lets say we have a pig spawner and 2 spawn events were fired, if it's a level 1 spawner than 2 pigs would spawn. If it were a level 2 spawner than 4 pigs would spawn. If only 1 spawn events were fired and it was a level 3 spawner then 3 pigs would spawn.

Note that the number of spawn events for a mob spawner that get fired at one time is completly random.

Download [here](https://github.com/valkyrienyanko/StackableSpawners/releases).

### Main Config Preview

```yml
# The max level that the spawner can be upgraded to.
maxSpawnerLevel: 3

# If creative mode is ignored then a normal mob spawner will be placed in creative.
ignoreCreativeMode: false

# Should everyone be able to use the spawners or do specific users have to use the "stackablespawners.use" permission?
ignorePermissions: true
```

### Spawners Config Preview

```yml
spawners:
  '1':
    loc:
      x: 3
      y: 4
      z: -5
    type: PIG
    level: 2
  '2':
    loc:
      x: 4
      y: 4
      z: -3
    type: WITHER_SKELETON
    level: 3
```
