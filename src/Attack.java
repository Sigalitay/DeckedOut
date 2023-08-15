public class Attack {
    int damage;
    Cooldown cooldown;//ms
    String name = "Basic";

    public Attack(int dmg, Cooldown cd)
    {
        damage = dmg;
        cooldown = cd;
    }
}
