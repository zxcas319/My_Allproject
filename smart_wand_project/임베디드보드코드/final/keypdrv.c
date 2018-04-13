#include <linux/miscdevice.h>
#include <linux/fs.h>
#include <linux/kernel.h>
#include <linux/module.h>
#include <asm/io.h>
#include <asm/uaccess.h>
#include <linux/init.h>
#include <linux/platform_device.h>

#define DIP_SW_ADDRESS (0x06000000 + 0x4000)
#define ADDRESS_MAP_SIZE 0x1000

MODULE_DESCRIPTION("System Programming practice 1, Keypad Driver");
MODULE_AUTHOR("Sysprog");
MODULE_LICENSE("GPL");

volatile unsigned short  *key_base;

int key_open(struct inode *inode, struct file *pfile)
{
	if(check_mem_region(DIP_SW_ADDRESS,ADDRESS_MAP_SIZE))
	{
		printk("keypdrv : memory already in use\n");
		return -EBUSY;
	}
	if(request_mem_region(DIP_SW_ADDRESS,ADDRESS_MAP_SIZE,"DIPSW")== NULL)
	{
		printk("keypdrv : fail to allocate mem region\n");
	}

	key_base = ioremap(DIP_SW_ADDRESS, ADDRESS_MAP_SIZE);
	if(key_base == NULL)
	{
		printk("keypdrv : fail to io mapping\n");
		release_mem_region(DIP_SW_ADDRESS,ADDRESS_MAP_SIZE);
		return -ENOMEM;
	}
	return 0;
}

ssize_t key_read(struct file *pfile, char *buf, size_t count, loff_t *filePos)
{
	unsigned short keypad_raw;
	unsigned int ret;
	int num = 0;
	int i,j;
	// read key
	for(i = 0; i < 4 ;i++ )
	{
		writew(0x10 << i,key_base ); 
		keypad_raw =readw(key_base) & 0xF;
		if ( keypad_raw > 0 ) 
		{
			for(j = 0; j < 4 ; j++ ) 
			{
				 if ( keypad_raw & (0x01<< j))
				 {
					num = i + 1 + j*4;
					break;
				 }
			}
		}

		if ( num )
		{
			break;
		}

	}

	ret = copy_to_user(buf,&num,4);	
	return 4;
}

int key_release(struct inode *inode,struct file *pfile)
{
	if(key_base != NULL)
	{
		iounmap(key_base);
		key_base = NULL;
		release_mem_region(DIP_SW_ADDRESS,ADDRESS_MAP_SIZE);
	}
	return 0;
}

struct file_operations key_fops =
{
	.owner = THIS_MODULE,
	.open = key_open,
	.read = key_read,
	.release = key_release
};

struct miscdevice key_device =
{
	.minor = MISC_DYNAMIC_MINOR,
	.name = "key",
	.fops = &key_fops,
};

static int __init key_init(void)
{
	int res;

	res = misc_register(&key_device);
	if(res)
	{
		printk("fail to regiter the device\n");
		return res;
	}
		return 0;
}

static void __exit key_exit(void)
{
	misc_deregister(&key_device);
}

module_init(key_init)
module_exit(key_exit)


