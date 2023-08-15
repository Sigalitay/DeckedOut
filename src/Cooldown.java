import java.time.LocalDateTime;

public class Cooldown {

    long startingTime;
    long cooldownLength;
    private boolean cooldownActive;

    public Cooldown(long cooldownLength)//in milli
    {
        this.cooldownLength = cooldownLength;
    }

    public boolean getCoolDownActive()
    {
        if(cooldownActive)
        {
            long timePassed = System.currentTimeMillis() - startingTime;
            if(timePassed >= cooldownLength)
                cooldownActive = false;
        }
        return cooldownActive;
    }

    public void activateCoolDown()
    {
        cooldownActive = true;
        startingTime = System.currentTimeMillis();
    }
}
//arrows
//38 = up
//37 = left
//40 = down
//39 = right