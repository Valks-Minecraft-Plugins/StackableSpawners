# StackableSpawners

Stack spawners together to upgrade how many mobs are spawned next time. You can right click on a spawner with your empty hand to check its level and type.

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
