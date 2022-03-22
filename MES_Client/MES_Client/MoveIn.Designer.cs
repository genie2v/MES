namespace MES_Client
{
    partial class MoveIn
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnReset = new System.Windows.Forms.Button();
            this.btnRun = new System.Windows.Forms.Button();
            this.textBoxLotId = new System.Windows.Forms.TextBox();
            this.labelProdQty = new System.Windows.Forms.Label();
            this.labelProd = new System.Windows.Forms.Label();
            this.labelFlow = new System.Windows.Forms.Label();
            this.labelOper = new System.Windows.Forms.Label();
            this.labelLotId = new System.Windows.Forms.Label();
            this.textBoxProdQty = new System.Windows.Forms.TextBox();
            this.textBoxOper = new System.Windows.Forms.TextBox();
            this.textBoxFlow = new System.Windows.Forms.TextBox();
            this.textBoxProd = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // btnReset
            // 
            this.btnReset.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnReset.Location = new System.Drawing.Point(147, 433);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(140, 49);
            this.btnReset.TabIndex = 23;
            this.btnReset.Text = "초기화";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // btnRun
            // 
            this.btnRun.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnRun.Location = new System.Drawing.Point(293, 433);
            this.btnRun.Name = "btnRun";
            this.btnRun.Size = new System.Drawing.Size(140, 49);
            this.btnRun.TabIndex = 22;
            this.btnRun.Text = "실행";
            this.btnRun.UseVisualStyleBackColor = true;
            this.btnRun.Click += new System.EventHandler(this.btnRun_Click);
            // 
            // textBoxLotId
            // 
            this.textBoxLotId.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.textBoxLotId.Location = new System.Drawing.Point(136, 35);
            this.textBoxLotId.Name = "textBoxLotId";
            this.textBoxLotId.Size = new System.Drawing.Size(149, 25);
            this.textBoxLotId.TabIndex = 21;
            this.textBoxLotId.KeyDown += new System.Windows.Forms.KeyEventHandler(this.textBoxLotId_KeyDown);
            // 
            // labelProdQty
            // 
            this.labelProdQty.AutoSize = true;
            this.labelProdQty.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelProdQty.Location = new System.Drawing.Point(12, 248);
            this.labelProdQty.Name = "labelProdQty";
            this.labelProdQty.Size = new System.Drawing.Size(118, 32);
            this.labelProdQty.TabIndex = 17;
            this.labelProdQty.Text = "Prod Qty";
            // 
            // labelProd
            // 
            this.labelProd.AutoSize = true;
            this.labelProd.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelProd.Location = new System.Drawing.Point(12, 193);
            this.labelProd.Name = "labelProd";
            this.labelProd.Size = new System.Drawing.Size(69, 32);
            this.labelProd.TabIndex = 16;
            this.labelProd.Text = "Prod";
            // 
            // labelFlow
            // 
            this.labelFlow.AutoSize = true;
            this.labelFlow.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelFlow.Location = new System.Drawing.Point(12, 138);
            this.labelFlow.Name = "labelFlow";
            this.labelFlow.Size = new System.Drawing.Size(69, 32);
            this.labelFlow.TabIndex = 15;
            this.labelFlow.Text = "Flow";
            // 
            // labelOper
            // 
            this.labelOper.AutoSize = true;
            this.labelOper.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelOper.Location = new System.Drawing.Point(12, 83);
            this.labelOper.Name = "labelOper";
            this.labelOper.Size = new System.Drawing.Size(71, 32);
            this.labelOper.TabIndex = 14;
            this.labelOper.Text = "Oper";
            // 
            // labelLotId
            // 
            this.labelLotId.AutoSize = true;
            this.labelLotId.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelLotId.Location = new System.Drawing.Point(12, 28);
            this.labelLotId.Name = "labelLotId";
            this.labelLotId.Size = new System.Drawing.Size(84, 32);
            this.labelLotId.TabIndex = 12;
            this.labelLotId.Text = "Lot ID";
            // 
            // textBoxProdQty
            // 
            this.textBoxProdQty.Location = new System.Drawing.Point(136, 258);
            this.textBoxProdQty.Name = "textBoxProdQty";
            this.textBoxProdQty.ReadOnly = true;
            this.textBoxProdQty.Size = new System.Drawing.Size(149, 25);
            this.textBoxProdQty.TabIndex = 13;
            // 
            // textBoxOper
            // 
            this.textBoxOper.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.textBoxOper.Location = new System.Drawing.Point(136, 93);
            this.textBoxOper.Name = "textBoxOper";
            this.textBoxOper.ReadOnly = true;
            this.textBoxOper.Size = new System.Drawing.Size(149, 25);
            this.textBoxOper.TabIndex = 24;
            // 
            // textBoxFlow
            // 
            this.textBoxFlow.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.textBoxFlow.Location = new System.Drawing.Point(136, 148);
            this.textBoxFlow.Name = "textBoxFlow";
            this.textBoxFlow.ReadOnly = true;
            this.textBoxFlow.Size = new System.Drawing.Size(149, 25);
            this.textBoxFlow.TabIndex = 25;
            // 
            // textBoxProd
            // 
            this.textBoxProd.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.textBoxProd.Location = new System.Drawing.Point(136, 203);
            this.textBoxProd.Name = "textBoxProd";
            this.textBoxProd.ReadOnly = true;
            this.textBoxProd.Size = new System.Drawing.Size(149, 25);
            this.textBoxProd.TabIndex = 26;
            // 
            // MoveIn
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(445, 511);
            this.Controls.Add(this.textBoxProd);
            this.Controls.Add(this.textBoxFlow);
            this.Controls.Add(this.textBoxOper);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.btnRun);
            this.Controls.Add(this.textBoxLotId);
            this.Controls.Add(this.labelProdQty);
            this.Controls.Add(this.labelProd);
            this.Controls.Add(this.labelFlow);
            this.Controls.Add(this.labelOper);
            this.Controls.Add(this.textBoxProdQty);
            this.Controls.Add(this.labelLotId);
            this.Name = "MoveIn";
            this.Text = "MoveIn";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnReset;
        private System.Windows.Forms.Button btnRun;
        private System.Windows.Forms.TextBox textBoxLotId;
        private System.Windows.Forms.Label labelProdQty;
        private System.Windows.Forms.Label labelProd;
        private System.Windows.Forms.Label labelFlow;
        private System.Windows.Forms.Label labelOper;
        private System.Windows.Forms.Label labelLotId;
        private System.Windows.Forms.TextBox textBoxProdQty;
        private System.Windows.Forms.TextBox textBoxOper;
        private System.Windows.Forms.TextBox textBoxFlow;
        private System.Windows.Forms.TextBox textBoxProd;

    }
}