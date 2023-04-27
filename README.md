# RHForceResourcePack
#### Configurable forced resourcepacks for your server ☄️ Perfect to prevent XRAY resourcepacks! (override)

![](https://img.shields.io/bstats/servers/11910?color=green?label=Servers) ![](https://img.shields.io/bstats/players/11910?color=green?label=Players)


```
❌ No BungeeCord support!
❌ No SHA1 hash support!
❌ No per-world resourcepacks!
```
If you are looking for these or similar features, please use this plugin:
[Force Resourcepacks](https://www.spigotmc.org/resources/force-resourcepacks.10499/)
by [Phoenix616](https://www.spigotmc.org/resources/authors/phoenix616.3651/).

## :blue_book: What does it do?
With RHForceResourcePack you have the possibility to force all players to have a resource pack.
This is super useful to prevent X-Ray resource packs and thus unfair gameplay!
Players who do not accept / download the resource pack can be automatically kicked.
VIPs can also be given skipping permission. The entire plugin is configurable!

## :camera: Preview (kick message)
![](https://www.spigotmc.org/attachments/screen1-jpg.764803)

## :ticket: Compatibility
*supports Spigot and Paper*   

**API version:**   1.13   
**Spigot version:**   1.9 1.10 1.11 1.12 1.13 1.14 1.15 1.16 1.17 1.18 1.19   

The plugin should be working fine on future Minecraft versions, as long as Spigot does not change their API. There will be no support for 1.8 or older!

## :floppy_disk: How to install?
+ download the latest release
+ configure the plugin, if needed
+ reload the plugin (`/resourepack reload`)
#### Recommended: Use [PlugmanX](https://www.spigotmc.org/resources/plugmanx.88135/) for manual plugin reloads

### Important!
Check the size of your resourcepack beforehand!
The resource pack may not have a larger file size than 250 MiB (Before 1.18: 100 MiB (≈ 100.8 MB)) (Before 1.15: 50 MiB (≈ 50.4 MB)).
Note that download success or failure is logged by the client, and not by the server.

## :bookmark: Permissions
+ rhfrp.*
+ rhfrp.skip
+ rhfrp.reload
+ rhfrp.resend
+ rhfrp.resendall

## :keyboard: Commands
#### Main command  
```
/resourcepack
```
Aliases: rp, frp

```
/resourcepack reload
/resourcepack resend
/resourcepack resendall
```

## :bar_chart: Metrics (bstats)
This plugin uses metrics. You can see the public charts [here](https://bstats.org/plugin/bukkit/RHForceResourcePack/11910). [^1]
[^1]:If you don't want to be included in the metrics, you can disable it in **/plugins/bstats/config.yml**
